package com.codingchallenge.recipes.controller.excpetionhandler;

import java.util.List;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public record ErrorResponse(
    String id,
    int status,
    String code,
    List<String> messages) {

  @Override
  public String toString() {
    return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE);
  }
}
