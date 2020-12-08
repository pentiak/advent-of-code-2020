package com.adventofcode.day8;

import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@Log4j2
public class Mark1Processor implements Processor {

  private final Map<String, Consumer<Instruction>> instructionRegistry = new HashMap<>();
  private final List<ProcessorEventListener> listeners = new ArrayList<>();
  private int nextInstructionPointer;
  private int accumulator;

  public Mark1Processor() {
    registerInstructions();
  }

  private void registerInstructions() {
    instructionRegistry.put("acc", this::acc);
    instructionRegistry.put("jmp", this::jmp);
    instructionRegistry.put("nop", this::nop);
  }

  @Override
  public boolean run(Software software) {
    resetState();
    log.info("Execution of a {} started", software);
    while (software.hasInstruction(nextInstructionPointer)) {
      Instruction instruction = software.getInstruction(nextInstructionPointer);
      instructionRegistry.getOrDefault(instruction.getMnemonic(), this::unknownInstruction).accept(instruction);
    }
    log.info("Execution of a {} stopped", software);
    return nextInstructionPointer == software.getNumOfInstructions();
  }

  private void resetState() {
    nextInstructionPointer = 0;
    accumulator = 0;
  }

  @Override
  public void addListener(ProcessorEventListener listener) {
    listeners.add(listener);
  }

  @Override
  public void terminateExecution() {
    log.info("Terminating execution");
    nextInstructionPointer = -1;
  }

  @Override
  public int getAccumulatorValue() {
    return accumulator;
  }

  private void incrementInstructionPointer(int delta) {
    setInstructionPointer(nextInstructionPointer + delta);
  }

  private void setInstructionPointer(int newInstructionPointer) {
    int previousInstructionPointer = nextInstructionPointer;
    nextInstructionPointer = newInstructionPointer;
    InstructionPointerChangedEvent event = InstructionPointerChangedEvent.builder()
        .previousInstructionPointer(previousInstructionPointer)
        .nextInstructionPointer(nextInstructionPointer)
        .build();
    publishEvent(event);
  }

  private void publishEvent(InstructionPointerChangedEvent event) {
    listeners.forEach(listener -> listener.instructionPointerChanged(event));
  }

  private void acc(Instruction instruction) {
    accumulator += instruction.getArgument();
    incrementInstructionPointer(1);
  }

  private void jmp(Instruction instruction) {
    incrementInstructionPointer(instruction.getArgument());
  }

  private void nop(Instruction instruction) {
    incrementInstructionPointer(1);
  }

  private void unknownInstruction(Instruction instruction) {
    log.error("Instruction {} is not supported", instruction);
    terminateExecution();
  }
}
