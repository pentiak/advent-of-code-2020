package com.adventofcode.day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class PolicyChecker {

  private final List<String> inputLines;
  private final PolicyFactory policyFactory;

  public PolicyChecker(PolicyFactory policyFactory, Path inputFile) {
    this.policyFactory = policyFactory;
    try {
      inputLines = List.copyOf(Files.readAllLines(inputFile));
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid input file", e);
    }
  }

  public long countValidPolicies() {
    return inputLines.stream()
        .map(line -> new PolicyPassword(policyFactory, line))
        .filter(PolicyPassword::isValid)
        .count();
  }
}
