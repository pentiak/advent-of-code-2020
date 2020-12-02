package com.adventofcode.day2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TobogganPolicy implements Policy {

  private static final Pattern INPUT_PATTERN = Pattern.compile("^(\\d+)-(\\d+) (\\w)$");
  private final int firstIdx;
  private final int secondIdx;
  private final char character;

  public TobogganPolicy(String policyString) {
    Matcher matcher = INPUT_PATTERN.matcher(policyString);
    if (!matcher.find()) {
      throw new IllegalArgumentException("Invalid policy String: " + policyString);
    }

    firstIdx = Integer.parseInt(matcher.group(1));
    secondIdx = Integer.parseInt(matcher.group(2));
    character = matcher.group(3).charAt(0);
  }

  @Override
  public boolean isValid(String password) {
    boolean firstIdxMatch = password.charAt(firstIdx - 1) == character;
    boolean secondIdxMatch = password.charAt(secondIdx - 1) == character;

    return firstIdxMatch ^ secondIdxMatch;
  }
}
