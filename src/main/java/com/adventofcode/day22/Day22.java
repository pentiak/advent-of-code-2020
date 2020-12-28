package com.adventofcode.day22;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day22 {

  public static void main(String[] args) {
    part1();
    part2();
  }

  private static void part2() {
    DeckLoader loader = new DeckLoader();
    Int2ObjectOpenHashMap<Deck> decks = loader.load("day22/input.txt");
    GameCounter counter = new GameCounter();

    CombatGame game = new RecursiveCombatGame(counter, decks);
    int winningPlayer = game.play();

    log.info("Winning player score: {}", game.calculateScore(winningPlayer));
  }

  private static void part1() {
    DeckLoader loader = new DeckLoader();
    Int2ObjectOpenHashMap<Deck> decks = loader.load("day22/input.txt");

    CombatGame game = new RegularCombatGame(decks.get(1), decks.get(2));
    int winningPlayer = game.play();
    log.info("Winning player score: {}", game.calculateScore(winningPlayer));
  }
}
