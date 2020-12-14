package com.adventofcode.day14;

import lombok.extern.log4j.Log4j2;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Log4j2
public class Day14 {

  public static void main(String[] args) {
    Map<BigInteger, BigInteger> memory = new HashMap<>();
    DecoderV1 decoder = new DecoderV1(memory);
    decoder.run("day14/input.txt");

    BigInteger sum = memory.values().stream().reduce(BigInteger::add).orElse(BigInteger.ZERO);
    log.info("Memory sum: {}", sum);

    Map<BigInteger, BigInteger> memory2 = new HashMap<>();
    DecoderV2 decoder2 = new DecoderV2(memory2);
    decoder2.run("day14/input.txt");

    BigInteger sum2 = memory2.values().stream().reduce(BigInteger::add).orElse(BigInteger.ZERO);
    log.info("Memory sum2: {}", sum2);
  }
}
