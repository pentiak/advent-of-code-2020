package com.adventofcode.tools;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputLoader {

  public static Path resourcePath(String filePath) {
    try {
      return Paths.get(Objects.requireNonNull(InputLoader.class.getClassLoader().getResource(filePath)).toURI());
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid filePath: " + filePath);
    }
  }
}
