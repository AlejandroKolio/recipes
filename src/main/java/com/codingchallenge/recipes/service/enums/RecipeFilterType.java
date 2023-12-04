package com.codingchallenge.recipes.service.enums;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RecipeFilterType {

  IS_VEGETARIAN("vegetarian"),
  SERVINGS("servings"),
  INGREDIENT_INCLUDE("include"),
  INGREDIENT_EXCLUDE("exclude"),
  TEXT_SEARCH("textSearch");

  private final String value;

  public static final Map<String, RecipeFilterType> ALL_FILTERS = new HashMap<>();

  static {
    for (var filterType : values()) {
      ALL_FILTERS.put(filterType.getValue(), filterType);
    }
  }

  public static RecipeFilterType of(String value) {
    return Optional.ofNullable(ALL_FILTERS.get(value))
        .orElseThrow(() -> new IllegalArgumentException("Unknown filter type: " + value));
  }
}
