package com.codingchallenge.recipes.repository.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("recipes")
@Accessors(chain = true)
@CompoundIndex(
    name = "recipe_search_index",
    def = "{'isVegetarian': 1, 'servings': 1, 'ingredients.name': 1}",
    background = true)
public record Recipe(
    @Id String id,
    String name,
    @Indexed boolean isVegetarian,
    @Indexed int servings,
    List<Ingredient> ingredients,
    @TextIndexed String instructions,
    @Indexed(unique = true) String uniqueSignature,
    @CreatedDate LocalDateTime createdDate,
    @LastModifiedDate LocalDateTime updatedDate,
    @Version Integer version
) {

  public static Recipe withNewSignature(Recipe recipe, String newSignature) {
    return new Recipe(
        recipe.id,
        recipe.name,
        recipe.isVegetarian,
        recipe.servings,
        recipe.ingredients,
        recipe.instructions,
        newSignature,
        recipe.createdDate,
        recipe.updatedDate,
        recipe.version
    );
  }

  public static Recipe createNew(String name, boolean isVegetarian, int servings, List<Ingredient> ingredients,
      String instructions) {
    return new Recipe(
        UUID.randomUUID().toString(),
        name,
        isVegetarian,
        servings,
        ingredients,
        instructions,
        null,
        null,
        null,
        null
    );
  }
}
