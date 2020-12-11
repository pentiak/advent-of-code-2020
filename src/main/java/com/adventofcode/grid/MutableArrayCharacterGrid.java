package com.adventofcode.grid;

public class MutableArrayCharacterGrid extends ArrayCharacterGrid {

  public MutableArrayCharacterGrid(String inputFile) {
    super(inputFile);
  }

  public MutableArrayCharacterGrid(ArrayCharacterGrid grid) {
    super(grid);
  }

  public void setValueAt(int x, int y, char c) {
    grid[y][x] = c;
  }
}
