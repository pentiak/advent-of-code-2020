package com.adventofcode.day8;

import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InfiniteLoopTrap implements ProcessorEventListener {

  private final Runnable reaction;
  private final IntSet visitedPointers = new IntOpenHashSet();

  public InfiniteLoopTrap(Runnable reaction) {
    this.reaction = reaction;
  }


  @Override
  public void instructionPointerChanged(InstructionPointerChangedEvent event) {
    if (!visitedPointers.add(event.getNextInstructionPointer())) {
      log.info("Detected infinite loop. Next instruction pointer is: {}", event.getNextInstructionPointer());
      reaction.run();
    }
  }

  public void respring() {
    visitedPointers.clear();
  }
}
