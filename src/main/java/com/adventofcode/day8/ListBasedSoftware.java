package com.adventofcode.day8;

import java.util.List;

public class ListBasedSoftware implements Software {

  private final List<Instruction> instructions;

  public ListBasedSoftware(List<Instruction> instructions) {
    this.instructions = instructions;
  }

  @Override
  public boolean hasInstruction(int index) {
    return index >= 0 && index < instructions.size();
  }

  @Override
  public Instruction getInstruction(int index) {
    return instructions.get(index);
  }
}
