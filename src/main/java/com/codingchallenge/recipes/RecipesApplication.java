package com.codingchallenge.recipes;

import com.codingchallenge.recipes.config.CacheProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(CacheProperties.class)
public class RecipesApplication {

  public static void main(String[] args) {
    SpringApplication.run(RecipesApplication.class, args);
  }

}
