package com.adventofcode.day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LuggageProcessorTest {

  @Test
  void numberOfBagsContaining() {
    assertEquals(4, new LuggageProcessor("day7/input.txt")
        .numberOfBagsContaining("shiny gold"));
  }

  @Test
  void numberOfBagsInside() {
    assertEquals(126, new LuggageProcessor("day7/input2.txt")
        .numberOfBagsInside("shiny gold"));
  }
}