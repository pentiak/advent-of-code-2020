package com.adventofcode.day12;

import com.adventofcode.tools.InputLoader;

import java.util.HashMap;
import java.util.Map;
import java.util.function.IntConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ControlPanel<T> {

  private static final Pattern INSTRUCTION_PATTERN = Pattern.compile("^(\\w)(\\d+)$");
  private static final IntConsumer UNKNWON_INSTRUCTION = v -> {
    throw new IllegalArgumentException("Unknown instruction encountered");
  };
  protected final Map<String, IntConsumer> controls = new HashMap<>();
  protected final T vehicle;

  protected ControlPanel(T vehicle) {
    this.vehicle = vehicle;
  }

  public void runInstructions(String inputFile) {
    InputLoader.inputLines(inputFile).forEach(this::runInstruction);
  }

  public void runInstruction(String instruction) {
    Matcher matcher = INSTRUCTION_PATTERN.matcher(instruction);
    try {
      if (matcher.matches()) {
        controls.getOrDefault(matcher.group(1), UNKNWON_INSTRUCTION).accept(Integer.parseInt(matcher.group(2)));
      }
    } catch (Exception e) {
      throw new RuntimeException("Can't execute instruction: '" + instruction + "'", e);
    }
  }
}
