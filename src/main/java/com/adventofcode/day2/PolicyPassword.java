package com.adventofcode.day2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolicyPassword {

  private static final Pattern INPUT_PATTERN = Pattern.compile("^((\\d+)-(\\d+) (\\w)): (\\w+)$");
  private final Policy policy;
  private final String password;

  public PolicyPassword(PolicyFactory policyFactory, String inputLine) {
    Matcher matcher = INPUT_PATTERN.matcher(inputLine);
    if (!matcher.find()) {
      throw new IllegalArgumentException("Incorrect inputLine: " + inputLine);
    }

    policy = policyFactory.createPolicy(matcher.group(1));
    password = matcher.group(5);
  }

  public boolean isValid() {
    return policy.isValid(password);
  }
}
