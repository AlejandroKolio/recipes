package com.codingchallenge.recipes;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.testcontainers.shaded.com.google.common.io.Files;

public class Fixture {

  private static final ObjectMapper mapper = createObjectMapper();

  private static ObjectMapper createObjectMapper() {
    return new ObjectMapper()
        .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
        .configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE, true)
        .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .registerModule(new JavaTimeModule())
        .registerModule(new ParameterNamesModule());
  }

  public static <E> E single(String fileName, Class<E> clazz) throws IOException {
    return mapper.readValue(fileContent(fileName), clazz);
  }

  public static <E> List<E> list(String fileName, Class<E> clazz) throws IOException {
    var node = mapper.readTree(fileContent(fileName));
    if (node.isArray()) {
      return mapper.readerForListOf(clazz).readValue(node);
    } else {
      return List.of(mapper.treeToValue(node, clazz));
    }
  }

  public static String fileContent(String fileName) throws IOException {
    return fromClasspath(Fixture.class, "/__files/" + fileName);
  }

  private static String fromClasspath(Class<?> resourcesParent, String path) throws IOException {
    if (resourcesParent == null && !path.startsWith("/")) {
      throw new IllegalArgumentException("Path should be absolute if resourcesParent is not specified");
    }

    URL resourceUrl = resourcesParent.getResource(path);
    if (resourceUrl == null) {
      throw new IllegalArgumentException("Resource " + path + " not found");
    }

    File file = new File(resourceUrl.getFile());
    return Files.asCharSource(file, StandardCharsets.UTF_8).read();
  }
}
