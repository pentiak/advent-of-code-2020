package com.adventofcode.day8;

import lombok.Builder;
import lombok.Getter;

@Builder
public class InstructionPointerChangedEvent implements ProcessorEvent {

  @Getter
  private final int previousInstructionPointer;

  @Getter
  private final int nextInstructionPointer;

}
