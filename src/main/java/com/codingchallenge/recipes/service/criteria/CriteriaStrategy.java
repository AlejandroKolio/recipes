package com.codingchallenge.recipes.service.criteria;

import org.springframework.data.mongodb.core.query.Query;

@FunctionalInterface
public interface CriteriaStrategy {

  void apply(Query query, String value);

}
