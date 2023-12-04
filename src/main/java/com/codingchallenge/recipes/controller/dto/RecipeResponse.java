package com.codingchallenge.recipes.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RecipeResponse {

  private String id;
  private String name;
  private boolean isVegetarian;
  private int servings;
  private List<IngredientResponse> ingredients;
  private String instructions;
  private LocalDateTime createdDate;
  private LocalDateTime updatedDate;
  private int version;
}
