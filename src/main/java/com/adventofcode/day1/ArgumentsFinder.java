package com.adventofcode.day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ArgumentsFinder {

  private final List<String> inputLines;

  public ArgumentsFinder(Path inputFile) {
    try {
      inputLines = List.copyOf(Files.readAllLines(inputFile));
    } catch (IOException e) {
      throw new IllegalArgumentException("Invalid input file", e);
    }
  }

  public List<Integer> findSumOfTwoArguments(int desiredSum) {
    int[] values = inputLines.stream().mapToInt(Integer::parseInt).sorted().toArray();
    int startIndex = 0;
    int endIndex = values.length - 1;
    int compares = 0;
    int sum = values[startIndex] + values[endIndex];

    while (startIndex < values.length - 1) {
      if (sum > desiredSum) {
        while (sum > desiredSum) {
          endIndex--;
          sum = values[startIndex] + values[endIndex];
        }
      } else if (sum < desiredSum) {
        while (sum < desiredSum) {
          endIndex++;
          sum = values[startIndex] + values[endIndex];
        }
      } else {
        break;
      }
      compares++;
      startIndex++;
      sum = values[startIndex] + values[endIndex];
    }
    log.info("Compares2: " + compares);
    return List.of(values[startIndex], values[endIndex]);
  }

  public List<Integer> findSumOfThreeArguments(int desiredSum) {
    int[] values = inputLines.stream().mapToInt(Integer::parseInt).sorted().toArray();
    int sum;
    int i;
    int j = 0;
    int k = 0;
    int compares = 0;

    outer:
    for (i = 0; i < values.length - 3; i++) {
      for (j = i + 1; j < values.length - 2; j++) {
        for (k = j + 1; k < values.length - 1; k++) {
          sum = values[i] + values[j] + values[k];
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
    return List.of(values[i], values[j], values[k]);
  }

}
