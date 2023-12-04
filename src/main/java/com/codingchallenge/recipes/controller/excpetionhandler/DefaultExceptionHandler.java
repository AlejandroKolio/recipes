package com.codingchallenge.recipes.controller.excpetionhandler;

import com.codingchallenge.recipes.service.exception.DuplicateRecipeException;
import com.codingchallenge.recipes.service.exception.RecipeNotFoundException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandler {

  @ExceptionHandler(ConstraintViolationException.class)
  public ResponseEntity<ErrorResponse> handleValidationErrors(ConstraintViolationException ex) {
    List<String> errors = ex.getConstraintViolations().stream()
        .map(ConstraintViolation::getMessage).toList();

    ErrorResponse errorResponse = new ErrorResponse(
        UUID.randomUUID().toString(),
        HttpStatus.BAD_REQUEST.value(),
        HttpStatus.BAD_REQUEST.getReasonPhrase(),
        errors
    );

    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(RecipeNotFoundException.class)
  public ResponseEntity<ErrorResponse> handleNotFoundException(RecipeNotFoundException ex) {
    List<String> errors = Collections.singletonList(ex.getMessage());

    ErrorResponse errorResponse = new ErrorResponse(
        UUID.randomUUID().toString(),
        HttpStatus.NOT_FOUND.value(),
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        errors
    );

    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(DuplicateRecipeException.class)
  public ResponseEntity<ErrorResponse> handleConflictException(DuplicateRecipeException ex) {
    List<String> errors = Collections.singletonList(ex.getMessage());

    ErrorResponse errorResponse = new ErrorResponse(
        UUID.randomUUID().toString(),
        HttpStatus.CONFLICT.value(),
        HttpStatus.CONFLICT.getReasonPhrase(),
        errors
    );

    return new ResponseEntity<>(errorResponse, new HttpHeaders(), HttpStatus.CONFLICT);
  }
}
