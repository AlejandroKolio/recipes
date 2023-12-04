package com.codingchallenge.recipes.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
@RequiredArgsConstructor
public class CacheConfig {

  public static final String RECIPE_CACHE_NAME = "recipe-cache";

  private final CacheProperties cacheProperties;

  @Bean
  public Caffeine caffeineConfig() {
    return Caffeine.newBuilder()
        .initialCapacity(50)
        .expireAfterWrite(cacheProperties.ttl(), TimeUnit.MINUTES);
  }

  @Bean
  public CacheManager cacheManager(Caffeine caffeine) {
    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
    caffeineCacheManager.setCaffeine(caffeine);
    return caffeineCacheManager;
  }
}
