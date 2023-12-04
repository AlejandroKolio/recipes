package com.codingchallenge.recipes.service.exception;

import java.io.Serial;
import lombok.NonNull;

public class DuplicateRecipeException extends RuntimeException {

  @Serial private static final long serialVersionUID = 1561739285526862331L;

  public DuplicateRecipeException() {
    super("recipe is already exists");
  }

  public DuplicateRecipeException(@NonNull String signature) {
    super(String.format("duplicate recipe with signature: %s", signature));
  }

}
