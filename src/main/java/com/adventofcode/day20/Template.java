package com.adventofcode.day20;

import com.adventofcode.utils.InputUtils;
import it.unimi.dsi.fastutil.ints.IntIntPair;

import java.util.ArrayList;
import java.util.List;

public class Template {

  private final List<IntIntPair> templateDots = new ArrayList<>();
  private final int rows;
  private final int columns;
  private final char dot;

  public Template(String inputFile, char dot) {
    this.dot = dot;
    int maxRow = 0;
    int maxCol = 0;
    char[][] monsterArray = InputUtils.inputCharArray(inputFile);
    for (int i = 0; i < monsterArray.length; i++) {
      for (int j = 0; j < monsterArray[i].length; j++) {
        if (monsterArray[i][j] == dot) {
          templateDots.add(IntIntPair.of(i, j));
          if (i > maxRow) {
            maxRow = i;
          }
          if (j > maxCol) {
            maxCol = j;
          }
        }
      }
    }
    this.rows = maxRow + 1;
    this.columns = maxCol + 1;
  }

  public boolean matches(char[][] arr, int startRow, int startColumn) {
    return templateDots.stream().allMatch(p -> arr[startRow + p.firstInt()][startColumn + p.secondInt()] == dot);
  }

  public void mark(char[][] arr, int startRow, int startColumn, char mark) {
    templateDots.forEach(p -> arr[startRow + p.firstInt()][startColumn + p.secondInt()] = mark);
  }

  public int getRows() {
    return rows;
  }

  public int getColumns() {
    return columns;
  }

  public int getDots() {
    return templateDots.size();
  }
}
