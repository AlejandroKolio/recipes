package com.codingchallenge.recipes.controller;

import com.codingchallenge.recipes.BaseIntegrationTest;
import com.codingchallenge.recipes.Fixture;
import com.codingchallenge.recipes.MongoInitializer;
import com.codingchallenge.recipes.repository.domain.Ingredient;
import com.codingchallenge.recipes.repository.domain.Recipe;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import reactor.core.publisher.Flux;

@ContextConfiguration(initializers = MongoInitializer.class)
class RecipeControllerTest extends BaseIntegrationTest {
  @Autowired
  private ReactiveMongoTemplate reactiveMongoTemplate;

  private List<Recipe> allRecipes;

  @BeforeEach
  void setUp() throws IOException {
    allRecipes = populateDb().share().collectList().block();
  }

  @AfterEach
  void tearDown() {
    reactiveMongoTemplate.dropCollection("recipes").subscribe();
  }

  @Test
  void getAllRecipes() {
    webTestClient.get()
        .uri("/recipes")
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("$[*].id").isNotEmpty()
        .jsonPath("$[*].name").isNotEmpty()
        .jsonPath("$[*].servings").isNotEmpty()
        .jsonPath("$[*].ingredients").isNotEmpty()
        .jsonPath("$[*].instructions").isNotEmpty()
        .jsonPath("$[*].createdDate").isNotEmpty()
        .jsonPath("$[*].updatedDate").isNotEmpty()
        .jsonPath("$[*].version").isNotEmpty();
  }

  @Test
  void getRecipe() {
    Recipe recipe = allRecipes.stream().findFirst().get();

    webTestClient.get()
        .uri("/recipes/" + recipe.id())
        .accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.id").isEqualTo(recipe.id())
        .jsonPath("$.name").isNotEmpty()
        .jsonPath("$.servings").isNotEmpty()
        .jsonPath("$.ingredients").isArray()
        .jsonPath("$.instructions").isNotEmpty()
        .jsonPath("$.createdDate").isNotEmpty()
        .jsonPath("$.updatedDate").isNotEmpty()
        .jsonPath("$.version").isNotEmpty();
  }

  @Test
  void createRecipe() {
    Recipe newRecipe = Recipe.createNew(
        "Spaghetti Carbonara",
        false,
        2,
        List.of(new Ingredient("pasta", "kg", "1")),
        "cook gently"
    );
    webTestClient.post()
        .uri("/recipes")
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(newRecipe)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody()
        .jsonPath("$.id").isNotEmpty()
        .jsonPath("$.name").isNotEmpty()
        .jsonPath("$.servings").isNotEmpty()
        .jsonPath("$.ingredients").isArray()
        .jsonPath("$.instructions").isNotEmpty()
        .jsonPath("$.createdDate").isNotEmpty()
        .jsonPath("$.updatedDate").isNotEmpty()
        .jsonPath("$.version").isNotEmpty();
  }

  @Test
  void updateRecipe() {
    Recipe recipe = allRecipes.stream().findFirst().get();
    Recipe updatedRecipe = Recipe.createNew(
        recipe.name(),
        recipe.isVegetarian(),
        recipe.servings(),
        recipe.ingredients(),
        "new instruction"
    );

    webTestClient.put()
        .uri("/recipes/" + recipe.id())
        .contentType(MediaType.APPLICATION_JSON)
        .bodyValue(updatedRecipe)
        .exchange()
        .expectStatus().isOk()
        .expectBody()
        .jsonPath("$.id").isNotEmpty()
        .jsonPath("$.name").isEqualTo(recipe.name())
        .jsonPath("$.servings").isEqualTo(recipe.servings())
        .jsonPath("$.ingredients").isNotEmpty()
        .jsonPath("$.instructions").isEqualTo(updatedRecipe.instructions())
        .jsonPath("$.createdDate").isNotEmpty()
        .jsonPath("$.updatedDate").isNotEmpty()
        .jsonPath("$.version").isNotEmpty();
  }

  @Test
  void deleteRecipe() {
  }

  private Flux<Recipe> populateDb() throws IOException {
    List<Recipe> recipes = Fixture.list("/request/recipes.json", Recipe.class);
    return reactiveMongoTemplate.insertAll(recipes);
  }
}
