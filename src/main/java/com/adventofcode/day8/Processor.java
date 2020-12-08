package com.adventofcode.day8;

public interface Processor {
  boolean run(Software software);

  void terminateExecution();

  int getAccumulatorValue();

  void addListener(ProcessorEventListener listener);
}
