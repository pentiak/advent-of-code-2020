package com.adventofcode.day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DocumentLoader {

  private static final Pattern INPUT_SPLIT = Pattern.compile("\\r?\\n\\r?\\n");
  private final String inputString;

  public DocumentLoader(Path inputFile) {
    try {
      inputString = Files.readString(inputFile);
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid input file: " + inputFile);
    }
  }

  public List<Document> loadDocuments() {
    return INPUT_SPLIT.splitAsStream(inputString).map(Document::new).collect(Collectors.toList());
  }
}
