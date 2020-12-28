package com.adventofcode.day23;

import lombok.extern.log4j.Log4j2;

import java.time.Duration;
import java.time.Instant;

@Log4j2
public class Day23 {

  public static void main(String[] args) {
    part1();
    part2();
  }

  private static void part1() {
    StringBuilder sb = new StringBuilder();
    Cups cups = new Cups("326519478");
    for (int i = 1; i <= 100; i++) {
      cups.move();
    }

    Cup cup1 = cups.getCup(1);
    Cup printCup = cup1;
    while ((printCup = printCup.getNextCup()) != cup1) {
      sb.append(printCup.getLabel());
    }
    log.info("Part1 answer: {}", sb.toString());
  }

  private static void part2() {
    Cups cups = new Cups("326519478", 1_000_000);
    Instant start = Instant.now();
    for (int i = 1; i <= 10_000_000; i++) {
      cups.move();
    }
    Instant finish = Instant.now();
    log.info("Moving time: {}", Duration.between(start, finish).toMillis());
    Cup first = cups.getCup(1).getNextCup();
    Cup second = first.getNextCup();
    log.info("Part2 answer: {}", (long) first.getLabel() * second.getLabel());
  }
}
