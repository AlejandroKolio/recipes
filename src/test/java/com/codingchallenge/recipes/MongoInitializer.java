package com.codingchallenge.recipes;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;

public class MongoInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  private static final int MONGO_PORT = 27017;
  @Container
  private static final MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

  @Override
  public void initialize(ConfigurableApplicationContext applicationContext) {
    mongoDBContainer.start();
    String mongoDbUri =
        "mongodb://" + mongoDBContainer.getHost() + ":" + mongoDBContainer.getMappedPort(MONGO_PORT);

    TestPropertyValues.of("spring.data.mongodb.uri=" + mongoDbUri + "/recipes").applyTo(applicationContext.getEnvironment());
  }

}
