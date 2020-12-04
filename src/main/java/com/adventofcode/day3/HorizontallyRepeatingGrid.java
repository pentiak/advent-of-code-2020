package com.adventofcode.day3;

public class HorizontallyRepeatingGrid implements CharacterGrid {

  private final CharacterGrid sourceGrid;

  public HorizontallyRepeatingGrid(CharacterGrid sourceGrid) {
    this.sourceGrid = sourceGrid;
  }

  @Override
  public int maxX() {
    return Integer.MAX_VALUE;
  }

  @Override
  public int minX() {
    return Integer.MIN_VALUE;
  }

  @Override
  public int maxY() {
    return sourceGrid.maxY();
  }

  @Override
  public int minY() {
    return sourceGrid.minY();
  }

  @Override
  public char valueAt(int x, int y) {
    return sourceGrid.valueAt(x % sourceGrid.maxX(), y);
  }

  @Override
  public boolean isCoordinateValid(int x, int y) {
    return y >= sourceGrid.minY() && y <= sourceGrid.maxY();
  }
}
