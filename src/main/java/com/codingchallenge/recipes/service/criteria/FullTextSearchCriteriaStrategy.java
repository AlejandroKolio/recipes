package com.codingchallenge.recipes.service.criteria;

import static com.codingchallenge.recipes.service.criteria.FullTextSearchCriteriaStrategy.CRITERIA_NAME;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Component;

@Component(CRITERIA_NAME)
public class FullTextSearchCriteriaStrategy implements CriteriaStrategy {

  public static final String CRITERIA_NAME = "textSearch";

  @Override
  public void apply(Query query, String value) {
    query.addCriteria(TextCriteria.forDefaultLanguage().matching(value));
  }

}
