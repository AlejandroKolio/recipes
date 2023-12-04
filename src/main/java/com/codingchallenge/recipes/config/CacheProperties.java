package com.codingchallenge.recipes.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties("cache")
public record CacheProperties(Long ttl) {

}
