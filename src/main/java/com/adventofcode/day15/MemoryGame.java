package com.adventofcode.day15;

import it.unimi.dsi.fastutil.ints.Int2IntLinkedOpenHashMap;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.IntCollection;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;

import java.util.function.IntConsumer;

@Log4j2
@ToString
public class MemoryGame {

  private final Int2IntMap memory = new Int2IntLinkedOpenHashMap();
  private int turn;
  private int lastNumber;
  private int turnsApart;

  public void memorizeStartingNumbers(IntCollection numbers) {
    numbers.forEach((IntConsumer) this::sayNumber);
  }

  public int autoPlay(int turnsToPlay) {
    while (this.turn < turnsToPlay) {
      sayNumber(turnsApart);
    }
    return lastNumber;
  }

  public void sayNumber(int number) {
    turn++;
    int previousEncounterTurn = memory.put(number, turn);
    turnsApart = previousEncounterTurn == 0 ? 0 : turn - previousEncounterTurn;
    lastNumber = number;
  }
}
