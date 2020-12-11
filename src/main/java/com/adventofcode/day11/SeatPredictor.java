package com.adventofcode.day11;

import com.adventofcode.grid.ArrayCharacterGrid;
import com.adventofcode.grid.CharacterGrid;
import com.adventofcode.grid.MutableArrayCharacterGrid;
import it.unimi.dsi.fastutil.chars.Char2ObjectMap;
import it.unimi.dsi.fastutil.chars.Char2ObjectOpenHashMap;

import java.util.HashSet;
import java.util.Set;

public class SeatPredictor {

  private static final char EMPTY = 'L';
  private static final char OCCUPIED = '#';
  private static final char FLOOR = '.';
  private static final Char2ObjectMap<CharacterGridFunction> NAIVE_RULES;
  private static final Char2ObjectMap<CharacterGridFunction> ACTUAL_RULES;

  static {
    NAIVE_RULES = new Char2ObjectOpenHashMap<>();
    NAIVE_RULES.put(OCCUPIED, SeatPredictor::emptyNearest4Seats);
    NAIVE_RULES.put(EMPTY, SeatPredictor::occupyEmptyNearestSeats);
    NAIVE_RULES.put(FLOOR, SeatPredictor::asIs);

    ACTUAL_RULES = new Char2ObjectOpenHashMap<>();
    ACTUAL_RULES.put(OCCUPIED, SeatPredictor::empty5SeatsInSight);
    ACTUAL_RULES.put(EMPTY, SeatPredictor::occupyEmptySeatsInSight);
    ACTUAL_RULES.put(FLOOR, SeatPredictor::asIs);
  }

  private final ArrayCharacterGrid grid;
  private final Char2ObjectMap<CharacterGridFunction> rules;

  private SeatPredictor(String inputFile, Char2ObjectMap<CharacterGridFunction> rules) {
    this.grid = new ArrayCharacterGrid(inputFile);
    this.rules = rules;
  }

  public static SeatPredictor naiveSeatPredictor(String inputFile) {
    return new SeatPredictor(inputFile, NAIVE_RULES);
  }

  public static SeatPredictor actualSeatPredictor(String inputFile) {
    return new SeatPredictor(inputFile, ACTUAL_RULES);
  }

  private static char asIs(CharacterGrid gr, int x, int y) {
    return gr.valueAt(x, y);
  }

  private static char emptyNearest4Seats(CharacterGrid grid, int x, int y) {
    char seat = grid.valueAt(x, y);
    int adjacentOccupiedCount = 0;
    for (int xi = -1; xi <= 1; xi++) {
      for (int yi = -1; yi <= 1; yi++) {
        if (xi == 0 && yi == 0) {
          continue;
        }
        int offsetX = x + xi;
        int offsetY = y + yi;
        if (grid.isCoordinateValid(offsetX, offsetY) && grid.valueAt(offsetX,
            offsetY) == OCCUPIED && ++adjacentOccupiedCount >= 4) {
          return EMPTY;
        }
      }
    }
    return seat;
  }

  private static char occupyEmptyNearestSeats(CharacterGrid grid, int x, int y) {
    char seat = grid.valueAt(x, y);
    for (int xi = -1; xi <= 1; xi++) {
      for (int yi = -1; yi <= 1; yi++) {
        if (xi == 0 && yi == 0) {
          continue;
        }
        int offsetX = x + xi;
        int offsetY = y + yi;
        if (grid.isCoordinateValid(offsetX, offsetY) && grid.valueAt(offsetX, offsetY) == OCCUPIED) {
          return seat;
        }
      }
    }
    return OCCUPIED;
  }

  private static char empty5SeatsInSight(CharacterGrid grid, int x, int y) {
    char seat = grid.valueAt(x, y);
    int adjacentOccupiedCount = 0;
    for (int xi = -1; xi <= 1; xi++) {
      for (int yi = -1; yi <= 1; yi++) {
        if (xi == 0 && yi == 0) {
          continue;
        }
        int offsetX = x + xi;
        int offsetY = y + yi;
        if (grid.isCoordinateValid(offsetX, offsetY)
            && SeatPredictor.firstSeatInDirection(grid, x, y, xi, yi) == OCCUPIED
            && ++adjacentOccupiedCount >= 5) {
          return EMPTY;
        }
      }
    }
    return seat;
  }

  private static char occupyEmptySeatsInSight(CharacterGrid grid, int x, int y) {
    char seat = grid.valueAt(x, y);
    for (int xi = -1; xi <= 1; xi++) {
      for (int yi = -1; yi <= 1; yi++) {
        if (xi == 0 && yi == 0) {
          continue;
        }
        int offsetX = x + xi;
        int offsetY = y + yi;
        if (grid.isCoordinateValid(offsetX, offsetY)
            && SeatPredictor.firstSeatInDirection(grid, x, y, xi, yi) == OCCUPIED) {
          return seat;
        }
      }
    }
    return OCCUPIED;
  }

  private static char firstSeatInDirection(CharacterGrid grid, int x, int y, int deltaX, int deltaY) {
    int nextX = x + deltaX;
    int nextY = y + deltaY;

    while (grid.isCoordinateValid(nextX, nextY)) {
      char nextPoint = grid.valueAt(nextX, nextY);
      if (nextPoint != FLOOR) {
        return nextPoint;
      }
      nextX += deltaX;
      nextY += deltaY;
    }
    return FLOOR;
  }

  public ArrayCharacterGrid getStabilizedState() {
    Set<ArrayCharacterGrid> states = new HashSet<>();
    ArrayCharacterGrid nextState = grid;
    while (states.add(nextState)) {
      nextState = predictNext(nextState);
    }
    return nextState;
  }

  public ArrayCharacterGrid predictNextState() {
    return predictNext(grid);
  }

  private ArrayCharacterGrid predictNext(ArrayCharacterGrid grid) {
    MutableArrayCharacterGrid newGrid = new MutableArrayCharacterGrid(grid);
    for (int x = 0; x <= grid.maxX(); x++) {
      for (int y = 0; y <= grid.maxY(); y++) {
        char currentState = grid.valueAt(x, y);
        newGrid.setValueAt(x, y, rules.get(currentState).apply(grid, x, y));
      }
    }
    return newGrid;
  }


}
