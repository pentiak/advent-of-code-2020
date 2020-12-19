package com.adventofcode.grid;

import com.adventofcode.utils.InputUtils;

import java.util.Arrays;
import java.util.List;

public class ArrayCharacterGrid implements CharacterGrid {

  protected final char[][] grid;

  public ArrayCharacterGrid(ArrayCharacterGrid other) {
    this.grid = new char[other.grid.length][];
    for (int i = 0; i < other.grid.length; i++) {
      this.grid[i] = Arrays.copyOf(other.grid[i], other.grid[i].length);
    }
  }

  public ArrayCharacterGrid(String inputFile) {
    List<String> lines = InputUtils.readInputLines(inputFile);
    grid = new char[lines.size()][];
    for (int i = 0; i < lines.size(); i++) {
      grid[i] = lines.get(i).toCharArray();
    }
  }

  @Override
  public int maxX() {
    return grid[0].length - 1;
  }

  @Override
  public int minX() {
    return 0;
  }

  @Override
  public int maxY() {
    return grid.length - 1;
  }

  @Override
  public int minY() {
    return 0;
  }

  @Override
  public char valueAt(int x, int y) {
    return grid[y][x];
  }

  @Override
  public boolean isCoordinateValid(int x, int y) {
    return x >= minX() && x <= maxX() && y >= minY() && y <= maxY();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    ArrayCharacterGrid that = (ArrayCharacterGrid) o;
    return Arrays.deepEquals(grid, that.grid);
  }

  @Override
  public int hashCode() {
    return Arrays.deepHashCode(grid);
  }

  public String visualize() {
    StringBuilder sb = new StringBuilder();
    for (int y = 0; y < grid.length; y++) {
      for (int x = 0; x < grid[y].length; x++) {
        sb.append(grid[y][x]);
      }
      sb.append(System.lineSeparator());
    }
    return sb.toString();
  }
}
