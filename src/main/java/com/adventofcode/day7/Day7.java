package com.adventofcode.day7;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day7 {


  public static void main(String[] args) {
    LuggageProcessor processor = new LuggageProcessor("day7/input.txt");
    int bagsContainingShinyGold = processor.numberOfBagsContaining("shiny gold");
    long bagsInsideShinyGold = processor.numberOfBagsInside("shiny gold");

    log.info("Bags containing shiny gold: {}", bagsContainingShinyGold);
    log.info("Bags inside shiny gold: {}", bagsInsideShinyGold);
  }
}
