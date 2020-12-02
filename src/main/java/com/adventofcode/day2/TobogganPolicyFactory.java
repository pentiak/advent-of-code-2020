package com.adventofcode.day2;

public class TobogganPolicyFactory implements PolicyFactory {

  @Override
  public Policy createPolicy(String policyInput) {
    return new TobogganPolicy(policyInput);
  }
}
