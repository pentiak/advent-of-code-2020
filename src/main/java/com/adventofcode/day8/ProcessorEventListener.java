package com.adventofcode.day8;

public interface ProcessorEventListener {
  void instructionPointerChanged(InstructionPointerChangedEvent event);
}
