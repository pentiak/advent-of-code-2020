package com.adventofcode.day3;

public interface CharacterGrid {

  char valueAt(int x, int y);

  boolean isCoordinateValid(int x, int y);
}
