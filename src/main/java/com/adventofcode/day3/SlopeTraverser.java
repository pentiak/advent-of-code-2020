package com.adventofcode.day3;

import com.adventofcode.grid.CharacterGrid;

import java.util.StringJoiner;

public class SlopeTraverser {

  private final CharacterGrid grid;
  private final int pathDeltaX;
  private final int pathDeltaY;
  private int treesMet = 0;

  public SlopeTraverser(CharacterGrid grid, int pathDeltaX, int pathDeltaY) {
    this.grid = grid;
    this.pathDeltaX = pathDeltaX;
    this.pathDeltaY = pathDeltaY;
  }

  public void traverse() {
    int x = 0;
    int y = 0;

    treesMet = 0;
    while (grid.isCoordinateValid(x, y)) {
      char value = grid.valueAt(x, y);
      if (value == '#') {
        treesMet++;
      }
      x += pathDeltaX;
      y += pathDeltaY;
    }
  }

  public int getTreesMet() {
    return treesMet;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", SlopeTraverser.class.getSimpleName() + "[", "]")
        .add("pathDeltaX=" + pathDeltaX)
        .add("pathDeltaY=" + pathDeltaY)
        .toString();
  }
}
