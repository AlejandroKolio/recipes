package com.codingchallenge.recipes.service;

import com.codingchallenge.recipes.repository.domain.Ingredient;
import com.codingchallenge.recipes.repository.domain.Recipe;
import java.util.Comparator;
import java.util.stream.Collectors;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

/**
 * Service for generating a unique signature for a recipe. This service provides functionality to create a hash-based
 * signature string that uniquely identifies a {@link Recipe} based on its name and ingredients.
 */
@Service
public class SignatureService {

  /**
   * Generates a unique signature for a given recipe. The signature is a SHA-256 hash of a concatenated string, which
   * includes the recipe's name and a sorted, concatenated string of its ingredients. Each ingredient is represented by
   * its string representation, typically including name and quantity.
   *
   * @param recipe The {@link Recipe} object for which the signature is to be generated.
   * @return A string representing the SHA-256 hash of the recipe's unique signature.
   */
  public String generateSignature(Recipe recipe) {
    String signatureString = recipe.name() + recipe.ingredients().stream()
        .sorted(Comparator.comparing(Ingredient::name))
        .map(Ingredient::toString)
        .collect(Collectors.joining());
    return DigestUtils.sha256Hex(signatureString);
  }

}
