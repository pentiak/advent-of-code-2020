package com.adventofcode.day10;

import com.adventofcode.utils.InputUtils;
import it.unimi.dsi.fastutil.ints.IntSet;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Log4j2
class AdapterChainTest {

  @Test
  void joltDifferences() {
    AdapterChain chain = new AdapterChain(InputUtils.inputIntList("day10/input.txt"));
    assertEquals(22, chain.getJoltDifferences(1));
    assertEquals(10, chain.getJoltDifferences(3));
  }

  @Test
  void joltDifferences2() {
    AdapterChain chain = new AdapterChain(InputUtils.inputIntList("day10/input2.txt"));
    assertEquals(7, chain.getJoltDifferences(1));
    assertEquals(0, chain.getJoltDifferences(2));
    assertEquals(5, chain.getJoltDifferences(3));
  }
}