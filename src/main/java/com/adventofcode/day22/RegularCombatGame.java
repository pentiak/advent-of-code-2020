package com.adventofcode.day22;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntComparators;
import it.unimi.dsi.fastutil.ints.IntList;

public class RegularCombatGame extends CombatGame {

  public RegularCombatGame(Deck firstDeck, Deck secondDeck) {
    players.put(1, firstDeck);
    players.put(2, secondDeck);
  }

  @Override
  public int play() {
    int winningPlayer = -1;
    while (playersHaveCards()) {
      winningPlayer = playRound();
    }
    return winningPlayer;
  }

  @Override
  protected int playRound() {
    int highestCard = -1;
    int roundWinningPlayer = -1;
    IntList placedCards = new IntArrayList();
    for (Int2ObjectMap.Entry<Deck> entry : players.int2ObjectEntrySet()) {
      int topCard = entry.getValue().getTopCard();
      placedCards.add(topCard);
      if (topCard > highestCard) {
        roundWinningPlayer = entry.getIntKey();
        highestCard = topCard;
      }
    }
    placedCards.sort(IntComparators.OPPOSITE_COMPARATOR);
    players.get(roundWinningPlayer).addOnBottom(placedCards);
    return roundWinningPlayer;
  }

}
