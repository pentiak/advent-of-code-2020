package com.adventofcode.day8;

public class SimpleInstructionFactory implements InstructionFactory {
  @Override
  public Instruction newInstruction(String instructionInput) {
    String[] split = instructionInput.split(" ");
    return Instruction.builder()
        .mnemonic(split[0])
        .argument(Integer.parseInt(split[1]))
        .build();
  }
}
