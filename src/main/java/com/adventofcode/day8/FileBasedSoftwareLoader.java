package com.adventofcode.day8;

import com.adventofcode.tools.InputLoader;

import java.util.List;
import java.util.stream.Collectors;

public class FileBasedSoftwareLoader implements SoftwareLoader {

  private final ListBasedSoftware software;

  public FileBasedSoftwareLoader(String filePath, InstructionFactory instructionFactory) {
    List<Instruction> instructions = InputLoader.inputLines(filePath)
        .map(instructionFactory::newInstruction)
        .collect(Collectors.toList());
    software = new ListBasedSoftware(filePath, instructions);
  }

  @Override
  public ListBasedSoftware load() {
    return software;
  }
}
