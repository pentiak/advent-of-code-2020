package com.adventofcode.day10;

import it.unimi.dsi.fastutil.ints.*;

public class AdapterChain {
  private final IntList input;

  public AdapterChain(IntList input) {
    this(input, true);
  }

  public AdapterChain(IntList input, boolean addExtremities) {
    this.input = new IntArrayList(input);
    this.input.sort(IntComparators.NATURAL_COMPARATOR);
    if (addExtremities) {
      this.input.add(0, 0);
      this.input.add(this.input.getInt(this.input.size() - 1) + 3);
    }
  }

  public int getJoltDifferences(int difference) {
    int count = 0;
    for (int i = 0; i < input.size() - 1; i++) {
      int diff = input.getInt(i + 1) - input.getInt(i);
      if (diff == difference) {
        count++;
      }
    }
    return count;
  }

  public IntSet getPossibleOmissions(int delta) {
    IntSet omissions = new IntOpenHashSet();
    for (int i = 0; i < input.size() - (delta - 1); i++) {
      int currentJolt = input.getInt(i);
      int maxJolt = currentJolt + delta;
      int j = i + (delta - 1);
      while (input.getInt(j) <= maxJolt) {
        omissions.add(input.getInt(j - 1));
        j++;
      }
    }
    return omissions;
  }

  public int getPossibleArrangements() {
    return -1;
  }
}
