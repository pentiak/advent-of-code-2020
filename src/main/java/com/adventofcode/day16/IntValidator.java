package com.adventofcode.day16;

import java.util.ArrayList;
import java.util.List;
import java.util.function.IntPredicate;

public class IntValidator implements IntPredicate {

  List<IntRange> ranges = new ArrayList<>();

  public void addRange(IntRange range) {
    ranges.add(range);
  }

  @Override
  public boolean test(int value) {
    return ranges.stream().anyMatch(p -> p.test(value));
  }
}
