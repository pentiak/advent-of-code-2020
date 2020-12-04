package com.adventofcode.day3;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileSourcedGrid implements CharacterGrid {

  private final char[][] sourceGrid;
  private final int maxX;
  private final int maxY;

  public FileSourcedGrid(Path sourceFile) {
    try {
      List<String> lines = Files.readAllLines(sourceFile);
      sourceGrid = new char[lines.size()][];
      int maxXLength = 0;
      for (int i = 0; i < lines.size(); i++) {
        sourceGrid[i] = lines.get(i).toCharArray();
        maxXLength = Math.max(maxXLength, sourceGrid[i].length);
      }
      maxX = maxXLength - 1;
      maxY = lines.size() - 1;
    } catch (Exception e) {
      throw new IllegalArgumentException("Invalid source file: " + sourceFile);
    }
  }

  @Override
  public int maxX() {
    return maxX;
  }

  @Override
  public int minX() {
    return 0;
  }

  @Override
  public int maxY() {
    return maxY;
  }

  @Override
  public int minY() {
    return 0;
  }

  @Override
  public char valueAt(int x, int y) {
    return sourceGrid[y][x];
  }

  @Override
  public boolean isCoordinateValid(int x, int y) {
    return x >= minX() && x <= maxX() && y >= minY() && y <= maxY();
  }
}
