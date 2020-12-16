package com.adventofcode.day15;

import it.unimi.dsi.fastutil.ints.IntList;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day15 {

  public static void main(String[] args) {
    MemoryGame game = new MemoryGame();
    game.memorizeStartingNumbers(IntList.of(0, 14, 1, 3, 7, 9));

    log.info("{} number spoken: {}", 2020, game.autoPlay(2020));
    log.info("{} number spoken: {}", 30000000, game.autoPlay(30000000));
  }
}
