package com.codingchallenge.recipes.service.exception;

import java.io.Serial;
import lombok.NonNull;

public class RecipeNotFoundException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1561739285526802331L;

  public RecipeNotFoundException() {
    super("recipe is not found");
  }

  public RecipeNotFoundException(@NonNull String recipeId) {
    super(String.format("recipe is not found, id=%s", recipeId));
  }

  public RecipeNotFoundException(String... args) {
    super(String.format("recipe is not found by provided filter criteria: %s", String.join(",", args)));
  }
}
