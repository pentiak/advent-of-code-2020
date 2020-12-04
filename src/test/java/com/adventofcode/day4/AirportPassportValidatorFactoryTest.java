package com.adventofcode.day4;

import static com.adventofcode.day4.AirportPassportValidatorFactory.checkBirthYear;
import static com.adventofcode.day4.AirportPassportValidatorFactory.checkHairColor;
import static com.adventofcode.day4.AirportPassportValidatorFactory.checkHeight;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AirportPassportValidatorFactoryTest {

  private final DocumentValidator validator = AirportPassportValidatorFactory.newAirportPassportValidator();

  @ParameterizedTest
  @ValueSource(strings = {"byr:2002"})
  void birthYearValid(String docString) {
    assertTrue(checkBirthYear(new Document(docString)));
  }

  @ParameterizedTest
  @ValueSource(strings = {"byr:2003"})
  void birthYearInvalid(String docString) {
    assertFalse(checkBirthYear(new Document(docString)));
  }

  @ParameterizedTest
  @ValueSource(strings = {"hgt:60in", "hgt:190cm"})
  void heightValid(String docString) {
    assertTrue(checkHeight(new Document(docString)));
  }

  @ParameterizedTest
  @ValueSource(strings = {"hgt:190in", "hgt:190"})
  void heightInvalid(String docString) {
    assertFalse(checkHeight(new Document(docString)));
  }

  @ParameterizedTest
  @ValueSource(strings = {"hcl:#123abc"})
  void hairColorValid(String docString) {
    assertTrue(checkHairColor(new Document(docString)));
  }

  @ParameterizedTest
  @ValueSource(strings = {"hcl:#123abz", "hcl:123abc"})
  void hairColorInvalid(String docString) {
    assertFalse(checkHairColor(new Document(docString)));
  }

  @ParameterizedTest
  @ValueSource(strings = {"pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980 hcl:#623a2f"})
  void validPassport(String passportInput) {
    assertTrue(validator.isValid(new Document(passportInput)));
  }
}