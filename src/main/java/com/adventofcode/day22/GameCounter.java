package com.adventofcode.day22;

public class GameCounter {
  private int game = 0;

  public int getNextGameNumber() {
    return ++game;
  }
}
