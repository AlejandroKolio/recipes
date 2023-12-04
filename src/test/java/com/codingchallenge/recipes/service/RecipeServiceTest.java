package com.codingchallenge.recipes.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.codingchallenge.recipes.Fixture;
import com.codingchallenge.recipes.controller.dto.IngredientResponse;
import com.codingchallenge.recipes.controller.dto.RecipeRequest;
import com.codingchallenge.recipes.controller.dto.RecipeResponse;
import com.codingchallenge.recipes.repository.RecipeRepository;
import com.codingchallenge.recipes.repository.domain.Recipe;
import com.codingchallenge.recipes.service.converter.RecipeRequestToRecipeConverter;
import com.codingchallenge.recipes.service.converter.RecipeToRecipeResponseConverter;
import com.codingchallenge.recipes.service.criteria.CriteriaStrategy;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {

  @Mock RecipeRepository recipeRepository;
  @Mock SignatureService signatureService;
  @Mock ReactiveMongoTemplate reactiveMongoTemplate;
  @Mock Map<String, CriteriaStrategy> criteriaStrategies;
  @Mock RecipeToRecipeResponseConverter responseConverter;
  @Mock RecipeRequestToRecipeConverter requestConverter;
  @InjectMocks RecipeService recipeService;

  private RecipeRequest recipeRequest;
  private RecipeResponse recipeResponse;
  private Recipe recipe;

  @BeforeEach
  void setUp() throws IOException {
    recipe = Fixture.single("/request/recipe.json", Recipe.class);
    recipeRequest = Fixture.single("/request/recipe.json", RecipeRequest.class);
    recipeResponse = Fixture.single("/request/recipe.json", RecipeResponse.class);
  }

  @Test
  void shouldCreateRecipeSuccessfully() {
    when(requestConverter.convert(any(RecipeRequest.class))).thenReturn(recipe);
    when(responseConverter.convert(any(Recipe.class))).thenReturn(recipeResponse);

    when(signatureService.generateSignature(recipe)).thenReturn("signature");
    when(recipeRepository.findByUniqueSignature(anyString())).thenReturn(Mono.empty());
    when(recipeRepository.save(any(Recipe.class))).thenReturn(Mono.just(recipe));

    Mono<RecipeResponse> result = recipeService.createRecipe(recipeRequest);

    StepVerifier.create(result)
        .expectNext(recipeResponse)
        .verifyComplete();
  }

  @Test()
  void getAllRecipes() throws IOException {
    List<Recipe> recipes = Fixture.list("/request/recipes.json", Recipe.class);
    assertNotNull(recipes, "Loaded recipes should not be null");
    assertFalse(recipes.isEmpty(), "Loaded recipes should not be empty");

    when(recipeRepository.findAll()).thenReturn(Flux.fromIterable(recipes));

    when(responseConverter.convert(any(Recipe.class))).thenReturn(recipeResponse);

    Flux<RecipeResponse> response = recipeService.getAllRecipes(Map.of());

    StepVerifier.create(response)
        .expectNextCount(recipes.size())
        .verifyComplete();
  }

  @Test
  void getRecipe() throws IOException {
    String recipeId = UUID.randomUUID().toString();
    Recipe mockRecipe = Fixture.single("/request/recipe.json", Recipe.class);
    RecipeResponse mockResponse = Fixture.single("/request/recipe.json", RecipeResponse.class);
    mockResponse.setId(recipeId);

    when(recipeRepository.findById(recipeId)).thenReturn(Mono.just(mockRecipe));
    when(responseConverter.convert(mockRecipe)).thenReturn(mockResponse);

    Mono<RecipeResponse> result = recipeService.getRecipe(recipeId);

    StepVerifier.create(result)
        .expectNext(mockResponse)
        .verifyComplete();
  }

  @Test
  void updateRecipe() throws IOException {
    Recipe existingRecipe = Fixture.single("/request/recipe.json", Recipe.class);
    RecipeRequest recipeRequest = Fixture.single("/request/recipe.json", RecipeRequest.class);
    Recipe updatedRecipe = Recipe.withNewSignature(existingRecipe, "new-signature");

    RecipeResponse updatedResponse = RecipeResponse.builder()
        .id(updatedRecipe.id())
        .name(updatedRecipe.name())
        .servings(updatedRecipe.servings())
        .ingredients(
            updatedRecipe.ingredients()
                .stream().map(it -> IngredientResponse.builder()
                    .name(it.name())
                    .unit(it.unit())
                    .quantity(it.quantity()).build()
                ).collect(Collectors.toList()))
        .isVegetarian(updatedRecipe.isVegetarian())
        .updatedDate(updatedRecipe.updatedDate())
        .createdDate(updatedRecipe.createdDate())
        .version(updatedRecipe.version())
        .build();

    when(recipeRepository.findById(existingRecipe.id())).thenReturn(Mono.just(existingRecipe));
    when(requestConverter.convert(recipeRequest)).thenReturn(updatedRecipe);
    when(recipeRepository.save(any(Recipe.class))).thenReturn(Mono.just(updatedRecipe));
    when(responseConverter.convert(updatedRecipe)).thenReturn(updatedResponse);

    Mono<RecipeResponse> result = recipeService.updateRecipe(existingRecipe.id(), recipeRequest);

    StepVerifier.create(result)
        .expectNext(updatedResponse)
        .verifyComplete();
  }

  @Test
  void deleteRecipe() throws IOException {
    Recipe existingRecipe = Fixture.single("/request/recipe.json", Recipe.class);

    RecipeResponse mockResponse = RecipeResponse.builder()
        .id(existingRecipe.id())
        .name(existingRecipe.name())
        .servings(existingRecipe.servings())
        .ingredients(
            existingRecipe.ingredients()
                .stream().map(it -> IngredientResponse.builder()
                    .name(it.name())
                    .unit(it.unit())
                    .quantity(it.quantity()).build()
                ).collect(Collectors.toList()))
        .isVegetarian(existingRecipe.isVegetarian())
        .updatedDate(existingRecipe.updatedDate())
        .createdDate(existingRecipe.createdDate())
        .version(existingRecipe.version())
        .build();

    when(recipeRepository.findById(existingRecipe.id())).thenReturn(Mono.just(existingRecipe));
    when(recipeRepository.deleteById(existingRecipe.id())).thenReturn(Mono.empty());
    when(responseConverter.convert(any(Recipe.class))).thenReturn(mockResponse);

    Mono<RecipeResponse> result = recipeService.deleteRecipe(existingRecipe.id());

    StepVerifier.create(result)
        .expectNext(mockResponse)
        .verifyComplete();

    Mockito.verify(recipeRepository).findById(anyString());
    Mockito.verify(recipeRepository).deleteById(anyString());
    Mockito.verify(responseConverter).convert(any(Recipe.class));
  }

}
