package com.codingchallenge.recipes;

import com.codingchallenge.recipes.service.SignatureService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = SignatureService.class)
public class RecipesApplicationTests {

  @Test
  void contextLoads() {
    System.out.println("test");
  }
}
