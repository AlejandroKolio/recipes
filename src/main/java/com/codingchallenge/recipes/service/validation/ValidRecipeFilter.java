package com.codingchallenge.recipes.service.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target({ElementType.FIELD, ElementType.TYPE_USE})
@Constraint(validatedBy = ValidRecipeFilterValidator.class)
public @interface ValidRecipeFilter {
  String message() default "unknown or invalid recipe type";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
