package com.adventofcode.day11;

import com.adventofcode.grid.ArrayCharacterGrid;
import com.adventofcode.grid.MutableArrayCharacterGrid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day11Test {

  @Test
  void steps() {
    ArrayCharacterGrid nextState = SeatPredictor.naiveSeatPredictor("day11/input.txt").predictNextState();
    assertEquals(new MutableArrayCharacterGrid("day11/input2.txt").visualize(), nextState.visualize());
    nextState = SeatPredictor.naiveSeatPredictor("day11/input2.txt").predictNextState();
    assertEquals(new MutableArrayCharacterGrid("day11/input3.txt").visualize(), nextState.visualize());
    nextState = SeatPredictor.naiveSeatPredictor("day11/input3.txt").predictNextState();
    assertEquals(new MutableArrayCharacterGrid("day11/input4.txt").visualize(), nextState.visualize());
    nextState = SeatPredictor.naiveSeatPredictor("day11/input4.txt").predictNextState();
    assertEquals(new MutableArrayCharacterGrid("day11/input5.txt").visualize(), nextState.visualize());
    nextState = SeatPredictor.naiveSeatPredictor("day11/input5.txt").predictNextState();
    assertEquals(new MutableArrayCharacterGrid("day11/input6.txt").visualize(), nextState.visualize());
  }

  @Test
  void stableState() {
    SeatPredictor predictor = SeatPredictor.naiveSeatPredictor("day11/input.txt");
    assertEquals(new MutableArrayCharacterGrid("day11/input6.txt").visualize(),
        predictor.getStabilizedState().visualize());
  }
}