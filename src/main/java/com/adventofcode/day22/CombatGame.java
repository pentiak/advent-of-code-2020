package com.adventofcode.day22;

import it.unimi.dsi.fastutil.ints.Int2ObjectLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.stream.IntStream;

public abstract class CombatGame {
  protected final Int2ObjectMap<Deck> players = new Int2ObjectLinkedOpenHashMap<>();

  public abstract int play();

  public int calculateScore(int player) {
    IntList cards = players.get(player).getCards();
    return IntStream.range(0, cards.size()).map(idx -> (cards.size() - idx) * cards.getInt(idx)).sum();
  }

  protected abstract int playRound();

  protected boolean playersHaveCards() {
    return players.values().stream().noneMatch(Deck::isEmpty);
  }
}
