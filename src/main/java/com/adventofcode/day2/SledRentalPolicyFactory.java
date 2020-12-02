package com.adventofcode.day2;

public class SledRentalPolicyFactory implements PolicyFactory {

  @Override
  public Policy createPolicy(String policyInput) {
    return new SledRentalPolicy(policyInput);
  }
}
