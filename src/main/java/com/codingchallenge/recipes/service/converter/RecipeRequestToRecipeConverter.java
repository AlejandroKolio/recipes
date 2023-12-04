package com.codingchallenge.recipes.service.converter;

import com.codingchallenge.recipes.controller.dto.RecipeRequest;
import com.codingchallenge.recipes.controller.dto.RecipeRequest.IngredientRequest;
import com.codingchallenge.recipes.repository.domain.Ingredient;
import com.codingchallenge.recipes.repository.domain.Recipe;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;

@Component
public class RecipeRequestToRecipeConverter {

  public Recipe convert(RecipeRequest recipeRequest) {
    List<Ingredient> ingredients = recipeRequest.getIngredients()
        .stream().map(this::toIngredient)
        .collect(Collectors.toList());
    return Recipe.createNew(
        recipeRequest.getName(),
        recipeRequest.isVegetarian(),
        recipeRequest.getServings(),
        ingredients,
        recipeRequest.getInstructions()
    );
  }

  private Ingredient toIngredient(IngredientRequest ingredientRequest) {
    return new Ingredient(
        ingredientRequest.getName(),
        ingredientRequest.getQuantity(),
        ingredientRequest.getUnit()
    );
  }
}
