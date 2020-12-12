package com.adventofcode.day12;

public interface MovementListener {

  void turnedLeft(int degrees);

  void turnedRight(int degrees);

  void movedForward(int distance);

  void movedNorth(int distance);

  void movedSouth(int distance);

  void movedEast(int distance);

  void movedWest(int distance);
}
