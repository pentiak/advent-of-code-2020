package com.adventofcode.day2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SledRentalPolicy implements Policy {

  private static final Pattern INPUT_PATTERN = Pattern.compile("^(\\d+)-(\\d+) (\\w)$");
  private final int minOcc;
  private final int maxOcc;
  private final char character;

  public SledRentalPolicy(String policyString) {
    Matcher matcher = INPUT_PATTERN.matcher(policyString);
    if (!matcher.find()) {
      throw new IllegalArgumentException("Invalid policy String: " + policyString);
    }

    minOcc = Integer.parseInt(matcher.group(1));
    maxOcc = Integer.parseInt(matcher.group(2));
    character = matcher.group(3).charAt(0);
  }

  @Override
  public boolean isValid(String password) {
    long sum = password.chars().filter(c -> c == character).count();
    return sum >= minOcc && sum <= maxOcc;
  }
}
