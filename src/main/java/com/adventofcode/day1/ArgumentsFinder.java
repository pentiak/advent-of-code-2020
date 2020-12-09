package com.adventofcode.day1;

import it.unimi.dsi.fastutil.longs.*;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ArgumentsFinder {

  private ArgumentsFinder() {
  }

  public static LongList findSumOfTwoArguments(LongCollection input, long desiredSum) {
    LongList workingCopy = new LongArrayList(input);
    workingCopy.sort(LongComparators.NATURAL_COMPARATOR);
    int startIndex = 0;
    int endIndex = workingCopy.size() - 1;
    int compares = 0;
    long sum = workingCopy.getLong(startIndex) + workingCopy.getLong(endIndex);

    while (startIndex < endIndex) {
      compares++;
      if (sum > desiredSum) {
        while (sum > desiredSum && endIndex > startIndex + 1) {
          compares++;
          endIndex--;
          sum = workingCopy.getLong(startIndex) + workingCopy.getLong(endIndex);
        }
      } else if (sum < desiredSum) {
        while (sum < desiredSum && endIndex < workingCopy.size() - 2) {
          compares++;
          endIndex++;
          sum = workingCopy.getLong(startIndex) + workingCopy.getLong(endIndex);
        }
      }

      if (sum == desiredSum) {
        break;
      }

      startIndex++;
      sum = workingCopy.getLong(startIndex) + workingCopy.getLong(endIndex);
    }
    log.info("Compares2: " + compares);
    if (sum == desiredSum) {
      return LongList.of(workingCopy.getLong(startIndex), workingCopy.getLong(endIndex));
    } else {
      return LongLists.EMPTY_LIST;
    }
  }

  public static LongList findSumOfThreeArguments(LongCollection input, long desiredSum) {
    LongList workingCopy = new LongArrayList(input);
    workingCopy.sort(LongComparators.NATURAL_COMPARATOR);
    long sum;
    int i;
    int j = 0;
    int k = 0;
    int compares = 0;

    outer:
    for (i = 0; i < workingCopy.size() - 3; i++) {
      for (j = i + 1; j < workingCopy.size() - 2; j++) {
        for (k = j + 1; k < workingCopy.size() - 1; k++) {
          sum = workingCopy.getLong(i) + workingCopy.getLong(j) + workingCopy.getLong(k);
          compares++;
          if (sum > desiredSum) {
            break;
          }
          if (sum == desiredSum) {
            break outer;
          }
        }
      }
    }
    log.info("Compares3: " + compares);
    return LongList.of(workingCopy.getLong(i), workingCopy.getLong(j), workingCopy.getLong(k));
  }

  public static LongList findSummingSublist(LongCollection input, long desiredSum) {
    LongList workingCopy = new LongArrayList(input);
    int startIndex = 0;
    int endIndex = 1;
    long sum = workingCopy.getLong(startIndex);

    for (; startIndex < workingCopy.size(); startIndex++) {
      for (; sum < desiredSum && endIndex < workingCopy.size(); endIndex++) {
        sum += workingCopy.getLong(endIndex);
      }
      if (sum == desiredSum) {
        return workingCopy.subList(startIndex, endIndex);
      }
      sum -= workingCopy.getLong(startIndex);
    }
    return LongLists.EMPTY_LIST;
  }

}
