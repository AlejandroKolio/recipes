package com.codingchallenge.recipes.service.criteria;

import static com.codingchallenge.recipes.service.criteria.ExcludeIngredientCriteriaStrategy.CRITERIA_NAME;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

@Component(CRITERIA_NAME)
public class ExcludeIngredientCriteriaStrategy implements CriteriaStrategy {

  public static final String CRITERIA_NAME = "exclude";

  @Override
  public void apply(Query query, String value) {
    query.addCriteria(Criteria.where(CRITERIA_NAME).ne(value));
  }

}
