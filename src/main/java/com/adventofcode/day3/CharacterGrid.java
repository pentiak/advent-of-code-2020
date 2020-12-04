package com.adventofcode.day3;

public interface CharacterGrid {

  int maxX();

  int minX();

  int maxY();

  int minY();

  char valueAt(int x, int y);

  boolean isCoordinateValid(int x, int y);
}
