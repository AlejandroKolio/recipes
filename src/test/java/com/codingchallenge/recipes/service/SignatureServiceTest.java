package com.codingchallenge.recipes.service;

import com.codingchallenge.recipes.Fixture;
import com.codingchallenge.recipes.repository.domain.Recipe;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class SignatureServiceTest {

  @InjectMocks
  private SignatureService signatureService;

  @Test
  void generateSignature() throws IOException {
    List<Recipe> recipes = Fixture.list("/request/recipes.json", Recipe.class);

    recipes.forEach(recipe -> Assertions.assertDoesNotThrow(() -> signatureService.generateSignature(recipe)));
  }
}
