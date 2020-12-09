package com.adventofcode.day9;

import com.adventofcode.day1.ArgumentsFinder;
import it.unimi.dsi.fastutil.longs.LongList;

public class XmasDecryptor {

  private final LongList input;
  private final int preambleSize;

  public XmasDecryptor(LongList input, int preambleSize) {
    this.input = input;
    this.preambleSize = preambleSize;
  }

  public long findCorruptedValue() {
    for (int i = preambleSize; i < input.size(); i++) {
      LongList sumSearch = input.subList(i - preambleSize, i);
      LongList sumOfTwoArguments = ArgumentsFinder.findSumOfTwoArguments(sumSearch, input.getLong(i));
      if (sumOfTwoArguments.isEmpty()) {
        return input.getLong(i);
      }
    }
    return -1;
  }
}
