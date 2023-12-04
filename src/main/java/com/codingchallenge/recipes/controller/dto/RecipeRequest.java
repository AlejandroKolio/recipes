package com.codingchallenge.recipes.controller.dto;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
public class RecipeRequest {

  @NotBlank(message = "Name is required and cannot be blank.")
  private String name;

  @Accessors(fluent = true)
  @NotNull(message = "Please specify whether the recipe is vegetarian.")
  private Boolean isVegetarian;

  @Positive(message = "Servings must be a positive number.")
  private int servings;

  @Size(min = 1, message = "At least one ingredient is required.")
  private List<@Valid IngredientRequest> ingredients;

  @NotBlank(message = "Instructions cannot be blank.")
  @Size(max = 2000, message = "Instructions cannot exceed 2000 characters.")
  private String instructions;

  @Data
  @Builder
  public static class IngredientRequest {

    @NotBlank(message = "Ingredient name cannot be blank.")
    private String name;
    @NotBlank(message = "Unit cannot be blank.")
    private String unit;
    @NotBlank(message = "Quantity cannot be blank.")
    private String quantity;
  }
}

