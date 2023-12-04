package com.codingchallenge.recipes.repository;

import com.codingchallenge.recipes.repository.domain.Recipe;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface RecipeRepository extends ReactiveMongoRepository<Recipe, String> {
  Mono<Recipe> findByUniqueSignature(String signature);

}
