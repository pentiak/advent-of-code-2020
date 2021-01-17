package com.adventofcode.day10;

import it.unimi.dsi.fastutil.ints.*;

public class AdapterChain {
  private final IntList input;
  private Int2LongMap memo;

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


  public long getAllPossibleConnections() {
    memo = new Int2LongOpenHashMap(input.size() - 1);
    return calculatePossibleConnectionTo(input.size() - 1);
  }

  private long calculatePossibleConnectionTo(int adapterIndex) {
    if (adapterIndex < 0) {
      return 0;
    }
    if (adapterIndex == 0) {
      return 1;
    }
    int adapter = input.getInt(adapterIndex);
    long cachedValue = memo.get(adapter);
    if (cachedValue != 0) {
      return cachedValue;
    }

    long sum = 0;
    for (int previousAdapterIndex = adapterIndex - 1;
         previousAdapterIndex >= adapterIndex - 3 && previousAdapterIndex >= 0;
         previousAdapterIndex--) {
      int previousAdapter = input.getInt(previousAdapterIndex);
      if (previousAdapter < adapter - 3) {
        break;
      }

      sum += calculatePossibleConnectionTo(previousAdapterIndex);
    }
    memo.put(adapter, sum);
    return sum;
  }
}
