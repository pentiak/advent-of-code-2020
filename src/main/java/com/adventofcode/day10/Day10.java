package com.adventofcode.day10;

import com.adventofcode.utils.InputUtils;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day10 {

  public static void main(String[] args) {
    part1();
    part2();
  }

  private static void part1() {
    AdapterChain chain = new AdapterChain(InputUtils.inputIntList("day10/input.txt"), true);
    int differencesOf1 = chain.getJoltDifferences(1);
    int differencesOf3 = chain.getJoltDifferences(3);

    log.info("Result: {}", differencesOf1 * differencesOf3);
  }

  private static void part2() {
    AdapterChain chain = new AdapterChain(InputUtils.inputIntList("day10/input.txt"));
    log.info("All possible connections: {}", chain.getAllPossibleConnections());
  }
}
