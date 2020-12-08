package com.adventofcode.day8;

import java.util.List;
import java.util.StringJoiner;

public class ListBasedSoftware implements Software {

  private final String name;
  private final List<Instruction> instructions;

  public ListBasedSoftware(String name, List<Instruction> instructions) {
    this.name = name;
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

  @Override
  public int getNumOfInstructions() {
    return instructions.size();
  }

  public void setInstruction(int index, Instruction instruction) {
    instructions.set(index, instruction);
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", ListBasedSoftware.class.getSimpleName() + "[", "]")
        .add("name='" + name + "'")
        .toString();
  }
}
