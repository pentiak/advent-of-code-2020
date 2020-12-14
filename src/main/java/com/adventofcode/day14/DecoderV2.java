package com.adventofcode.day14;

import com.google.common.math.IntMath;

import java.math.BigInteger;
import java.util.Map;

public class DecoderV2 extends Decoder {

  public DecoderV2(Map<BigInteger, BigInteger> memory) {
    super(memory);
  }

  @Override
  protected void runMemsetDirective(BigInteger address, BigInteger value) {

    int flipBitsCount = getFlipBitsCount();
    BigInteger flipInteger = BigInteger.ZERO;

    for (int flipRepeat = 0; flipRepeat < IntMath.pow(2, flipBitsCount); flipRepeat++) {
      int flipIndex = 0;
      for (int maskIndex = 0; maskIndex < mask.length; maskIndex++) {
        char maskBit = mask[maskIndex];
        int bitIndex = mask.length - 1 - maskIndex;
        if (maskBit == '1') {
          address = address.setBit(bitIndex);
        } else if (maskBit == 'X') {
          if (flipInteger.testBit(flipIndex)) {
            address = address.setBit(bitIndex);
          } else {
            address = address.clearBit(bitIndex);
          }
          flipIndex++;
        }
      }
      memory.put(address, value);
      flipInteger = flipInteger.add(BigInteger.ONE);
    }
  }

  private int getFlipBitsCount() {
    int flipBitsCount = 0;
    for (char c : mask) {
      if ('X' == c) {
        flipBitsCount++;
      }
    }
    return flipBitsCount;
  }
}
