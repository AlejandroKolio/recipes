package com.codingchallenge.recipes.service;

import static com.codingchallenge.recipes.config.CacheConfig.RECIPE_CACHE_NAME;

import com.codingchallenge.recipes.controller.dto.RecipeRequest;
import com.codingchallenge.recipes.controller.dto.RecipeResponse;
import com.codingchallenge.recipes.repository.RecipeRepository;
import com.codingchallenge.recipes.repository.domain.Recipe;
import com.codingchallenge.recipes.service.converter.RecipeRequestToRecipeConverter;
import com.codingchallenge.recipes.service.converter.RecipeToRecipeResponseConverter;
import com.codingchallenge.recipes.service.criteria.CriteriaStrategy;
import com.codingchallenge.recipes.service.enums.RecipeFilterType;
import com.codingchallenge.recipes.service.exception.DuplicateRecipeException;
import com.codingchallenge.recipes.service.exception.RecipeNotFoundException;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeService {

  private final RecipeRepository recipeRepository;
  private final SignatureService signatureService;
  private final ReactiveMongoTemplate reactiveMongoTemplate;
  private final Map<String, CriteriaStrategy> criteriaStrategies;
  private final RecipeToRecipeResponseConverter responseConverter;
  private final RecipeRequestToRecipeConverter requestConverter;

  public Mono<RecipeResponse> createRecipe(RecipeRequest recipeRequest) {
    Recipe recipe = requestConverter.convert(recipeRequest);
    String signature = signatureService.generateSignature(recipe);

    return recipeRepository.findByUniqueSignature(signature)
        .flatMap(duplicateRecipe -> Mono.<RecipeResponse>error(new DuplicateRecipeException(signature)))
        .switchIfEmpty(Mono.defer(() -> saveRecipeWithSignature(recipe, signature)))
        .doOnError(e -> log.error("Error creating recipe {}: {}", recipeRequest, e.getMessage()));
  }

  @Cacheable(value = RECIPE_CACHE_NAME, keyGenerator = "cacheKeyGenerator")
  public Flux<RecipeResponse> getAllRecipes(Map<String, String> criteria) {
    if (criteria.isEmpty()) {
      return recipeRepository.findAll().map(responseConverter::convert);
    } else {
      Query query = buildCriteria(criteria);
      return reactiveMongoTemplate.find(query, Recipe.class)
          .map(responseConverter::convert)
          .switchIfEmpty(Flux.empty())
          .onErrorResume(error ->
              Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                  "error fetching recipes: " + error.getMessage())));
    }
  }

  @Cacheable(value = RECIPE_CACHE_NAME, key = "#recipeId")
  public Mono<RecipeResponse> getRecipe(String recipeId) {
    return recipeRepository.findById(recipeId)
        .map(responseConverter::convert)
        .switchIfEmpty(Mono.error(new RecipeNotFoundException(recipeId)));
  }

  @CachePut(value = RECIPE_CACHE_NAME, key = "#recipeId")
  public Mono<RecipeResponse> updateRecipe(String recipeId, RecipeRequest recipeRequest) {
    return recipeRepository.findById(recipeId)
        .flatMap(recipe -> {
          Recipe toUpdate = requestConverter.convert(recipeRequest);
          return Mono.just(Recipe.withNewSignature(toUpdate, signatureService.generateSignature(toUpdate)));
        }).flatMap(recipeRepository::save)
        .map(responseConverter::convert)
        .switchIfEmpty(Mono.error(new RecipeNotFoundException(recipeId)))
        .onErrorResume(error -> {
          if (error instanceof DuplicateKeyException) {
            return Mono.error(new DuplicateRecipeException());
          } else {
            return Mono.error(new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                "error updating recipe: " + error.getMessage()));
          }
        });
  }


  @CacheEvict(value = RECIPE_CACHE_NAME, key = "#recipeId")
  public Mono<RecipeResponse> deleteRecipe(String recipeId) {
    return recipeRepository.findById(recipeId)
        .flatMap(recipe -> recipeRepository.deleteById(recipeId)
            .then(Mono.just(responseConverter.convert(recipe))))
        .switchIfEmpty(Mono.error(new RecipeNotFoundException(recipeId)));
  }

  private Mono<RecipeResponse> saveRecipeWithSignature(Recipe recipe, String signature) {
    Recipe withNewSignature = Recipe.withNewSignature(recipe, signature);
    return recipeRepository.save(withNewSignature)
        .map(responseConverter::convert)
        .doOnError(e -> log.error("Error saving recipe with signature {}: {}", signature, e.getMessage()));
  }

  private Query buildCriteria(Map<String, String> criteria) {
    Query query = new Query();
    criteria.forEach((key, value) -> addCriteriaToQuery(query, RecipeFilterType.of(key), value));
    return query;
  }

  private void addCriteriaToQuery(Query query, RecipeFilterType recipeFilterType, String value) {
    Optional.ofNullable(criteriaStrategies.get(recipeFilterType.getValue())).ifPresent(it -> it.apply(query, value));
  }
}
