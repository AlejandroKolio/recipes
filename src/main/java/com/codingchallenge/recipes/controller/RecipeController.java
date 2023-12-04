package com.codingchallenge.recipes.controller;

import com.codingchallenge.recipes.controller.dto.RecipeRequest;
import com.codingchallenge.recipes.controller.dto.RecipeResponse;
import com.codingchallenge.recipes.service.RecipeService;
import com.codingchallenge.recipes.service.validation.ValidRecipeFilter;
import java.util.Map;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/recipes")
public class RecipeController {

  private final RecipeService recipeService;

  @GetMapping
  public Flux<RecipeResponse> getAllRecipes(
      @RequestParam(required = false) @Valid Map<@ValidRecipeFilter String, String> criteria) {
    return recipeService.getAllRecipes(criteria);
  }

  @GetMapping("/{recipeId}")
  public Mono<RecipeResponse> getRecipe(@PathVariable(name = "recipeId") String recipeId) {
    return recipeService.getRecipe(recipeId);
  }

  @PostMapping
  public Mono<RecipeResponse> createRecipe(@RequestBody RecipeRequest recipeRequest) {
    return recipeService.createRecipe(recipeRequest);
  }

  @PutMapping("/{recipeId}")
  public Mono<RecipeResponse> updateRecipe(@PathVariable(name = "recipeId") String recipeId, @RequestBody RecipeRequest recipeRequest) {
    return recipeService.updateRecipe(recipeId, recipeRequest);
  }

  @DeleteMapping("/{recipeId}")
  public Mono<RecipeResponse> deleteRecipe(@PathVariable(name = "recipeId") String recipeId) {
    return recipeService.deleteRecipe(recipeId);
  }
}
