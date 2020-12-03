package com.adventofcode.day3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class HorizontallyRepeatingGrid implements CharacterGrid {

  private final char[][] sourceGrid;
  private final int xLength;

  public HorizontallyRepeatingGrid(Path sourceFile) {
    try {
      List<String> lines = Files.readAllLines(sourceFile);
      sourceGrid = new char[lines.size()][];
      int maxXLength = 0;
      for (int i = 0; i < lines.size(); i++) {
        sourceGrid[i] = lines.get(i).toCharArray();
        maxXLength = Math.max(maxXLength, sourceGrid[i].length);
      }
      xLength = maxXLength;
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid source file: " + sourceFile);
    }
  }

  @Override
  public char valueAt(int x, int y) {
    return sourceGrid[y][x % xLength];
  }

  @Override
  public boolean isCoordinateValid(int x, int y) {
    return y >= 0 && y < sourceGrid.length;
  }
}
