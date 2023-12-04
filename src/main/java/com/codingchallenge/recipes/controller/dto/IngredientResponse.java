package com.codingchallenge.recipes.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IngredientResponse {

  private String name;
  private String unit;
  private String quantity;
}
