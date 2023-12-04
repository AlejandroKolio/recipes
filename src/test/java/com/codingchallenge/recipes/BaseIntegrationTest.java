package com.codingchallenge.recipes;

import com.github.tomakehurst.wiremock.WireMockServer;
import java.time.Duration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.MockServerSpec;

@ActiveProfiles("test")
@AutoConfigureWireMock(port = 0)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = RecipesApplication.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BaseIntegrationTest {

  protected WebTestClient webTestClient;

  @Autowired(required = false)
  protected WireMockServer wireMockServer;

  @BeforeEach
  public void setUp(ApplicationContext context) {
    wireMockServer.resetAll();
    MockServerSpec<?> mockServerSpec = WebTestClient.bindToApplicationContext(context);
    WebTestClient.Builder webTestClientBuilder = mockServerSpec
        .configureClient()
        .baseUrl("https://task.recipe.com")
        .responseTimeout(Duration.ofMinutes(10));
    configureWebTestClient(webTestClientBuilder);
    webTestClient = webTestClientBuilder.build();
  }

  protected void configureWebTestClient(WebTestClient.Builder webTestClientBuilder) {
  }
}
