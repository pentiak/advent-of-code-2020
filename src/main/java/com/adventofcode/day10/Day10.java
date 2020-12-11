package com.adventofcode.day10;

import com.adventofcode.tools.InputLoader;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day10 {

  public static void main(String[] args) {
    AdapterChain chain = new AdapterChain(InputLoader.inputIntList("day10/input.txt"));
    int differencesOf1 = chain.getJoltDifferences(1);
    int differencesOf3 = chain.getJoltDifferences(3);

    log.info("Result: {}", differencesOf1 * differencesOf3);
    log.info("Possible arrangements: {}", chain.getPossibleArrangements());
  }
}
