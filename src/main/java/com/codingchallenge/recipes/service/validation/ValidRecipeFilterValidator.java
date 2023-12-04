package com.codingchallenge.recipes.service.validation;

import static java.util.stream.Collectors.toSet;

import com.codingchallenge.recipes.service.enums.RecipeFilterType;
import java.util.EnumSet;
import java.util.Set;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ValidRecipeFilterValidator implements ConstraintValidator<ValidRecipeFilter, String> {

  private final static String MSG_TEMPLATE = "invalid recipe type: '%s'. Valid types are: %s";
  private final Set<String> knownRecipeFilterTypes;

  public ValidRecipeFilterValidator() {
    this.knownRecipeFilterTypes = EnumSet.allOf(RecipeFilterType.class).stream()
        .map(RecipeFilterType::getValue)
        .collect(toSet());
  }

  @Override
  public boolean isValid(String recipeType, ConstraintValidatorContext context) {
    log.debug("recipe filter type: {}", recipeType);
    if (recipeType == null) {
      return true;
    }
    if (!knownRecipeFilterTypes.contains(recipeType)) {
      var message = MSG_TEMPLATE.formatted(recipeType, knownRecipeFilterTypes);
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(message)
          .addConstraintViolation();
      return false;
    }
    return true;
  }
}
