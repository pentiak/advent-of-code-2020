package com.adventofcode.day14;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DecoderV1Test {

  @Test
  void testV1() {
    Map<BigInteger, BigInteger> memory = new HashMap<>();
    DecoderV1 initProgram = new DecoderV1(memory);
    initProgram.runLine("mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X");
    initProgram.runLine("mem[8] = 11");
    assertEquals(BigInteger.valueOf(73), memory.get(BigInteger.valueOf(8)));
  }

  @Test
  void testV2() {
    Map<BigInteger, BigInteger> memory = new HashMap<>();
    DecoderV2 initProgram = new DecoderV2(memory);
    initProgram.runLine("mask = 000000000000000000000000000000X1001X");
    initProgram.runLine("mem[42] = 100");
    assertEquals(BigInteger.valueOf(100), memory.get(BigInteger.valueOf(26)));
    assertEquals(BigInteger.valueOf(100), memory.get(BigInteger.valueOf(27)));
    assertEquals(BigInteger.valueOf(100), memory.get(BigInteger.valueOf(58)));
    assertEquals(BigInteger.valueOf(100), memory.get(BigInteger.valueOf(59)));
  }
}