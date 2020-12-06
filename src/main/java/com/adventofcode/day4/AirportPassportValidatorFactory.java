package com.adventofcode.day4;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AirportPassportValidatorFactory {

  private static final Pattern HEIGHT_PATTERN = Pattern.compile("(\\d+)(in|cm)");
  private static final Pattern HAIR_COLOR_PATTERN = Pattern.compile("#[0-9a-f]{6}");
  private static final Pattern EYE_COLOR_PATTERN = Pattern.compile("amb|blu|brn|gry|grn|hzl|oth");
  private static final Pattern PASSPORT_ID_PATTERN = Pattern.compile("\\d{9}");

  private static final List<Predicate<Document>> AIRPORT_PASSPORT_REQUIREMENTS = List.of(
      AirportPassportValidatorFactory::checkBirthYear, AirportPassportValidatorFactory::checkIssueYear,
      AirportPassportValidatorFactory::checkExpirationYear, AirportPassportValidatorFactory::checkHeight,
      AirportPassportValidatorFactory::checkHairColor, AirportPassportValidatorFactory::checkEyeColor,
      AirportPassportValidatorFactory::checkPassportId);

  public static boolean checkBirthYear(Document doc) {
    String byr = doc.getValue("byr");
    if (byr != null) {
      try {
        int birthYear = Integer.parseInt(byr);
        return birthYear >= 1920 && birthYear <= 2002;
      } catch (NumberFormatException e) {
        return false;
      }
    }
    return false;
  }

  public static boolean checkIssueYear(Document doc) {
    String iyr = doc.getValue("iyr");
    if (iyr != null) {
      try {
        int birthYear = Integer.parseInt(iyr);
        return birthYear >= 2010 && birthYear <= 2020;
      } catch (NumberFormatException e) {
        return false;
      }

    }
    return false;
  }

  public static boolean checkExpirationYear(Document doc) {
    String eyr = doc.getValue("eyr");
    if (eyr != null) {
      try {
        int birthYear = Integer.parseInt(eyr);
        return birthYear >= 2020 && birthYear <= 2030;
      } catch (NumberFormatException e) {
        return false;
      }

    }
    return false;
  }

  public static boolean checkHeight(Document doc) {
    String hgt = doc.getValue("hgt");
    if (hgt != null) {
      Matcher matcher = HEIGHT_PATTERN.matcher(hgt);

      if (matcher.matches()) {
        int height = Integer.parseInt(matcher.group(1));
        String unit = matcher.group(2);
        if ("cm".equals(unit)) {
          return height >= 150 && height <= 193;
        } else if ("in".equals(unit)) {
          return height >= 59 && height <= 76;
        }
      }
    }
    return false;
  }

  public static boolean checkHairColor(Document doc) {
    String hcl = doc.getValue("hcl");
    if (hcl != null) {
      return HAIR_COLOR_PATTERN.matcher(hcl).matches();
    }
    return false;
  }

  private static boolean checkEyeColor(Document doc) {
    String ecl = doc.getValue("ecl");
    if (ecl != null) {
      return EYE_COLOR_PATTERN.matcher(ecl).matches();
    }
    return false;
  }

  public static boolean checkPassportId(Document doc) {
    String pid = doc.getValue("pid");
    if (pid != null) {
      return PASSPORT_ID_PATTERN.matcher(pid).matches();
    }
    return false;
  }

  public static DocumentValidator newAirportPassportValidator() {
    return new ConfigurableValidator(AIRPORT_PASSPORT_REQUIREMENTS);
  }


}
