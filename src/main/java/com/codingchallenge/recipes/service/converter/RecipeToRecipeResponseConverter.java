package com.codingchallenge.recipes.service.converter;

import com.codingchallenge.recipes.controller.dto.IngredientResponse;
import com.codingchallenge.recipes.controller.dto.RecipeResponse;
import com.codingchallenge.recipes.repository.domain.Recipe;
import java.util.List;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeResponseConverter {

  public RecipeResponse convert(Recipe recipe) {
    List<IngredientResponse> ingredients = recipe.ingredients()
        .stream().filter(Objects::nonNull)
        .map(it -> IngredientResponse.builder()
            .name(it.name())
            .quantity(it.quantity())
            .unit(it.unit())
            .build())
        .toList();

    return RecipeResponse.builder()
        .id(recipe.id())
        .name(recipe.name())
        .servings(recipe.servings())
        .isVegetarian(recipe.isVegetarian())
        .instructions(recipe.instructions())
        .ingredients(ingredients)
        .createdDate(recipe.createdDate())
        .updatedDate(recipe.updatedDate())
        .version(recipe.version())
        .build();
  }
}
