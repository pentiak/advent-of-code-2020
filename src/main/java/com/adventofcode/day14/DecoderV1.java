package com.adventofcode.day14;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;

import java.math.BigInteger;
import java.util.Map;

public class DecoderV1 extends Decoder {

  public DecoderV1(Map<BigInteger, BigInteger> memory) {
    super(memory);
  }

  @Override
  protected void runMemsetDirective(BigInteger address, BigInteger value) {
    for (int i = 0; i < mask.length; i++) {
      char maskBit = mask[i];
      int bitIndex = mask.length - 1 - i;
      if (maskBit == '0') {
        value = value.clearBit(bitIndex);
      } else if (maskBit == '1') {
        value = value.setBit(bitIndex);
      }
    }
    memory.put(address, value);
  }
}
