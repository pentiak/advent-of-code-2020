package com.adventofcode.day2;

import com.adventofcode.tools.InputLoader;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day2 {

  public static void main(String[] args) {
    PolicyChecker sledRentalChecker = new PolicyChecker(new SledRentalPolicyFactory(),
        InputLoader.resourcePath("day2/input.txt"));
    log.info("Correct sled rental passwords: {}", sledRentalChecker.countValidPolicies());

    PolicyChecker tobogganChecker = new PolicyChecker(new TobogganPolicyFactory(),
        InputLoader.resourcePath("day2/input.txt"));
    log.info("Correct toboggan passwords: {}", tobogganChecker.countValidPolicies());
  }
}
