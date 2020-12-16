package com.adventofcode.day16;

import java.util.function.IntPredicate;

public class IntRange implements IntPredicate {

  private final int minValue;
  private final int maxValue;

  private IntRange(int minValue, int maxValue) {
    this.minValue = minValue;
    this.maxValue = maxValue;
  }

  public static IntRange of(int minValue, int maxValue) {
    return new IntRange(minValue, maxValue);
  }

  @Override
  public boolean test(int value) {
    return value >= minValue && value <= maxValue;
  }
}
