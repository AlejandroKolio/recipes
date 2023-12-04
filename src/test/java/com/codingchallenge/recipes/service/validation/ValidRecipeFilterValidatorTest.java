package com.codingchallenge.recipes.service.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.codingchallenge.recipes.service.enums.RecipeFilterType;
import java.util.Arrays;
import java.util.stream.Stream;
import javax.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ValidRecipeFilterValidatorTest {

  @Mock
  private ConstraintValidatorContext context;
  @Mock
  private ConstraintValidatorContext.ConstraintViolationBuilder violationBuilder;
  @InjectMocks
  private ValidRecipeFilterValidator validator;

  @ParameterizedTest
  @MethodSource("args")
  public void isValid(String filterType) {
    assertThat(validator.isValid(filterType, context)).isTrue();
  }

  @Test
  public void isValidEmpty() {

    assertThat(validator.isValid(null, context)).isTrue();
  }

  @Test
  void testIsValidWithInvalidRecipeType() {
    String invalidType = "AAA";

    MockitoAnnotations.openMocks(this);
    validator = new ValidRecipeFilterValidator();

    when(context.buildConstraintViolationWithTemplate(anyString())).thenReturn(violationBuilder);
    when(violationBuilder.addConstraintViolation()).thenReturn(context);

    boolean result = validator.isValid(invalidType, context);

    assertThat(result).isFalse();
    verify(context).disableDefaultConstraintViolation();
    verify(context).buildConstraintViolationWithTemplate(anyString());
    verify(violationBuilder).addConstraintViolation();
  }

  private static Stream<Arguments> args() {
    return Arrays.stream(RecipeFilterType.values())
        .map(RecipeFilterType::getValue)
        .map(Arguments::arguments);
  }

}
