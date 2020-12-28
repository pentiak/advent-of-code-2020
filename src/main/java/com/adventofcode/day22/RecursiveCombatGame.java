package com.adventofcode.day22;

import it.unimi.dsi.fastutil.ints.*;
import lombok.extern.log4j.Log4j2;

import java.util.HashSet;
import java.util.Set;

@Log4j2
public class RecursiveCombatGame extends CombatGame {

  private final int game;
  private final GameCounter counter;
  private final Set<Int2ObjectMap<Deck>> previousRounds = new HashSet<>();
  private int round = 0;

  public RecursiveCombatGame(GameCounter counter, Int2ObjectMap<Deck> players) {
    this.game = counter.getNextGameNumber();
    this.counter = counter;
    for (Int2ObjectMap.Entry<Deck> entry : players.int2ObjectEntrySet()) {
      this.players.put(entry.getIntKey(), entry.getValue());
    }
  }

  @Override
  public int play() {
    log.info("=== Game {} ===\n", game);
    int winningPlayer = -1;
    while (playersHaveCards()) {
      if (previousRounds.contains(players)) {
        winningPlayer = 1;
        break;
      }
      winningPlayer = playRound();
    }
    log.info("The winner of game {} is player {}!", game, winningPlayer);
    return winningPlayer;
  }

  @Override
  protected int playRound() {
    round++;
    log.info("-- Round {} (Game {}) --", round, game);
    savePreiousState();
    logPlayersDecks();

    int highestCard = -1;
    int cardHierarchyWinningPlayer = -1;
    Int2IntMap placedCards = new Int2IntLinkedOpenHashMap();
    boolean allHaveEnoughCards = true;
    for (Int2ObjectMap.Entry<Deck> entry : players.int2ObjectEntrySet()) {
      int topCard = entry.getValue().getTopCard();
      placedCards.put(entry.getIntKey(), topCard);
      log.info("Player {} plays: {}", entry.getIntKey(), topCard);

      if (topCard > entry.getValue().getCards().size()) {
        allHaveEnoughCards = false;
      }

      if (topCard > highestCard) {
        cardHierarchyWinningPlayer = entry.getIntKey();
        highestCard = topCard;
      }
    }


    if (allHaveEnoughCards) {
      log.info("Playing a sub-game to determine the winner...\n");
      Int2ObjectMap<Deck> newDecks = copyDecks(placedCards);
      CombatGame subGame = new RecursiveCombatGame(counter, newDecks);
      int subGameWinningPlayer = subGame.play();
      players.get(subGameWinningPlayer).addOnBottom(placedCards.remove(subGameWinningPlayer));
      IntList allPlacedCards = new IntArrayList(placedCards.values());
      allPlacedCards.sort(IntComparators.OPPOSITE_COMPARATOR);
      players.get(subGameWinningPlayer).addOnBottom(allPlacedCards);
      log.info("Player {} wins round {} of game {}!\n", subGameWinningPlayer, round, game);
      return subGameWinningPlayer;
    } else {
      IntList allPlacedCards = new IntArrayList(placedCards.values());
      allPlacedCards.sort(IntComparators.OPPOSITE_COMPARATOR);
      players.get(cardHierarchyWinningPlayer).addOnBottom(allPlacedCards);
      log.info("Player {} wins round {} of game {}!\n", cardHierarchyWinningPlayer, round, game);
      return cardHierarchyWinningPlayer;
    }
  }

  private void logPlayersDecks() {
    for (Int2ObjectMap.Entry<Deck> entry : players.int2ObjectEntrySet()) {
      log.info("Player {}'s deck {}", entry.getIntKey(), entry.getValue());
    }
  }

  private void savePreiousState() {
    Int2ObjectMap<Deck> previousRound = new Int2ObjectLinkedOpenHashMap<>();
    for (Int2ObjectMap.Entry<Deck> entry : players.int2ObjectEntrySet()) {
      previousRound.put(entry.getIntKey(), entry.getValue().copyOf(-1));
    }
    previousRounds.add(previousRound);
  }

  private Int2ObjectMap<Deck> copyDecks(Int2IntMap placedCards) {
    Int2ObjectMap<Deck> newPlayers = new Int2ObjectLinkedOpenHashMap<>();
    for (Int2IntMap.Entry entry : placedCards.int2IntEntrySet()) {
      newPlayers.put(entry.getIntKey(), players.get(entry.getIntKey()).copyOf(entry.getIntValue()));
    }
    return newPlayers;
  }
}
