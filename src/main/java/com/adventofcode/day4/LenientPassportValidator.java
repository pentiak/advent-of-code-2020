package com.adventofcode.day4;

import java.util.List;

public class LenientPassportValidator implements DocumentValidator {

  private final List<String> requiredFields = List.of("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid");

  @Override
  public boolean isValid(Document document) {
    return requiredFields.stream().allMatch(document::isFieldPresent);
  }
}
