package com.adventofcode.day8;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder = true)
public class Instruction {

  @Getter
  private final String mnemonic;

  @Getter
  private final int argument;
}
