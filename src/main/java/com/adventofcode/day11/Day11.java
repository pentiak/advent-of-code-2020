package com.adventofcode.day11;

import com.adventofcode.grid.CharacterGrid;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day11 {

  public static void main(String[] args) {
    part1();
    part2();
  }

  private static void part1() {
    SeatPredictor predictor = SeatPredictor.naiveSeatPredictor("day11/input.txt");
    CharacterGrid stabilizedState = predictor.getStabilizedState();
    int occupiedCount = 0;
    for (int x = 0; x <= stabilizedState.maxX(); x++) {
      for (int y = 0; y <= stabilizedState.maxY(); y++) {
        if (stabilizedState.valueAt(x, y) == '#') {
          occupiedCount++;
        }
      }
    }
    log.info("Naive occupied seats: {}", occupiedCount);
  }

  private static void part2() {
    SeatPredictor predictor = SeatPredictor.actualSeatPredictor("day11/input.txt");
    CharacterGrid stabilizedState = predictor.getStabilizedState();
    int occupiedCount = 0;
    for (int x = 0; x <= stabilizedState.maxX(); x++) {
      for (int y = 0; y <= stabilizedState.maxY(); y++) {
        if (stabilizedState.valueAt(x, y) == '#') {
          occupiedCount++;
        }
      }
    }
    log.info("Actual occupied seats: {}", occupiedCount);
  }
}
