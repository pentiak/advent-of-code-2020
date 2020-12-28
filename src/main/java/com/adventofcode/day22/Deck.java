package com.adventofcode.day22;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;

import java.util.LinkedList;
import java.util.Objects;

public class Deck {

  private final LinkedList<Integer> queue = new LinkedList<>();

  public void addOnBottom(int card) {
    queue.offer(card);
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }

  public int getTopCard() {
    return queue.poll();
  }

  public void addOnBottom(IntList placedCards) {
    for (int i = 0; i < placedCards.size(); i++) {
      addOnBottom(placedCards.getInt(i));
    }
  }

  public IntList getCards() {
    return new IntArrayList(queue);
  }

  public Deck copyOf(int cardsNum) {
    int limit = cardsNum == -1 ? queue.size() : cardsNum;
    Deck newDeck = new Deck();
    for (int i = 0; i < limit; i++) {
      newDeck.addOnBottom(queue.get(i));
    }
    return newDeck;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Deck deck = (Deck) o;
    return queue.equals(deck.queue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(queue);
  }

  @Override
  public String toString() {
    return queue.toString();
  }
}
