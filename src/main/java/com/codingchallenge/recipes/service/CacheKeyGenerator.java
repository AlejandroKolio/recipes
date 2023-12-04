package com.codingchallenge.recipes.service;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

@Component
public class CacheKeyGenerator implements KeyGenerator {

  @Override
  public Object generate(Object target, Method method, Object... params) {
    final Map<String, String> criteria = (Map<String, String>) params[0];
    return criteria.entrySet().stream()
        .sorted(Map.Entry.comparingByKey())
        .map(entry -> entry.getKey() + ":" + entry.getValue())
        .collect(Collectors.joining(","));
  }
}
