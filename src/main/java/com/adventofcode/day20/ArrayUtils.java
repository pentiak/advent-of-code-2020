package com.adventofcode.day20;

import java.util.Arrays;

public class ArrayUtils {

  private ArrayUtils() {
  }

  public static char[][] rotateRight(char[][] content) {
    char[][] rotContent = new char[content[0].length][];
    for (int i = 0; i < content.length; i++) {
      for (int j = 0; j < content[i].length; j++) {
        if (i == 0) {
          rotContent[j] = new char[content.length];
        }
        rotContent[j][content.length - 1 - i] = content[i][j];
      }
    }
    return rotContent;
  }

  public static char[][] flipHorizontal(char[][] content) {
    char[][] flipContent = new char[content.length][];
    for (int i = 0; i < content.length; i++) {
      flipContent[content.length - 1 - i] = Arrays.copyOf(content[i], content[i].length);
    }
    return flipContent;
  }

  public static char[][] flipVertical(char[][] content) {
    char[][] flipContent = new char[content.length][];
    for (int i = 0; i < content.length; i++) {
      for (int j = 0; j < content[i].length; j++) {
        if (j == 0) {
          flipContent[i] = new char[content.length];
        }
        flipContent[i][content[i].length - 1 - j] = content[i][j];
      }
    }
    return flipContent;
  }

  public static void printCharArray(char[][] arr) {
    for (int i = 0; i < arr.length; i++) {
      for (int j = 0; j < arr[i].length; j++) {
        System.out.print(arr[i][j]);
      }
      System.out.println();
    }
  }

  public static int arrMatchTemplate(char[][] arr, Template template) {
    int matchesFound = 0;
    for (int arrRow = 0; arrRow < arr.length - template.getRows() + 1; arrRow++) {
      for (int arrCol = 0; arrCol < arr[arrRow].length - template.getColumns() + 1; arrCol++) {
        if (template.matches(arr, arrRow, arrCol)){
          matchesFound++;
//          template.mark(arr, arrRow, arrCol, 'O');
        }
      }
    }
    return matchesFound;
  }

  public static int countCharacters(char[][] puzzleChars, char dot) {
    int count = 0;
    for (int i = 0; i < puzzleChars.length; i++) {
      for (int j = 0; j < puzzleChars[i].length; j++) {
        if (puzzleChars[i][j] == dot) {
          count++;
        }
      }
    }
    return count;
  }
}
