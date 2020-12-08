package com.adventofcode.day8;

import lombok.extern.log4j.Log4j2;
import org.jetbrains.annotations.NotNull;

@Log4j2
public class Day8 {

  public static void main(String[] args) {
    part1();
    part2();
  }

  private static void part1() {
    ListBasedSoftware software = new FileBasedSoftwareLoader("day8/input.txt", new SimpleInstructionFactory()).load();
    Mark1Processor processor = new Mark1Processor();
    processor.addListener(new InfiniteLoopTrap(processor::terminateExecution));
    processor.run(software);
    log.info("Accumulator state before infinite loop: {}", processor.getAccumulatorValue());
  }

  private static void part2() {
    FileBasedSoftwareLoader loader = new FileBasedSoftwareLoader("day8/input.txt", new SimpleInstructionFactory());
    HackingSoftware software = new HackingSoftware(loader.load());
    Mark1Processor processor = new Mark1Processor();


    InfiniteLoopTrap infiniteLoopTrap = createTrap(software, processor);
    processor.addListener(infiniteLoopTrap);

    while (!processor.run(software)) {
      log.info("Software terminated prematurely");
      infiniteLoopTrap.respring();
    }
    log.info("Accumulator state after successful execution: {}", processor.getAccumulatorValue());
  }

  @NotNull
  private static InfiniteLoopTrap createTrap(HackingSoftware software, Mark1Processor processor) {
    return new InfiniteLoopTrap(new Runnable() {
      private int triedIndex = 0;

      @Override
      public void run() {
        software.removeHacks();
        boolean fixApplied = false;
        for (; !fixApplied && triedIndex < software.getNumOfInstructions(); triedIndex++) {
          Instruction instruction = software.getInstruction(triedIndex);
          if ("jmp".equals(instruction.getMnemonic())) {
            software.addHack(triedIndex, instruction.toBuilder().mnemonic("nop").build());
            fixApplied = true;
          } else if ("nop".equals(instruction.getMnemonic())) {
            software.addHack(triedIndex, instruction.toBuilder().mnemonic("jmp").build());
            fixApplied = true;
          }
        }
        processor.terminateExecution();
      }
    });
  }
}
