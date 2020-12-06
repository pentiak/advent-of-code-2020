package com.adventofcode.tools;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.regex.Pattern;
import java.util.stream.Stream;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputLoader {

  private static final Pattern INPUT_SPLIT = Pattern.compile("\\n\\n");

  public static Path resourcePath(String filePath) {
    try {
      return Paths.get(Objects.requireNonNull(InputLoader.class.getClassLoader().getResource(filePath)).toURI());
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid filePath: " + filePath);
    }
  }

  public static Stream<String> inputSplitByBlankLine(String filePath) {
    try {
      return INPUT_SPLIT.splitAsStream(Files.readString(resourcePath(filePath)));
    } catch (IOException e) {
      throw new IllegalArgumentException("Can't read file: " + filePath);
    }
  }
}
