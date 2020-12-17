package com.adventofcode.day17;

import com.google.common.math.IntMath;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class Coordinate {

  private final int[] dimensionIndexes;

  private Coordinate(int[] dimensionIndexes) {
    this.dimensionIndexes = dimensionIndexes;
  }

  public static Coordinate of(int x, int y, int z) {
    return new Coordinate(new int[]{x, y, z});
  }

  public static Coordinate of(int x, int y, int z, int w) {
    return new Coordinate(new int[]{x, y, z, w});
  }

  public List<Coordinate> getAdjacentCoords() {
    int adjacentCount = IntMath.pow(3, dimensionIndexes.length) - 1;
    List<Coordinate> adjacentList = new ArrayList<>(adjacentCount);

    int[] delta = new int[dimensionIndexes.length];
    for (int i = 0; i <= adjacentCount; i++) {
      setDelta(delta, i);
      int[] adjacentCoord = new int[dimensionIndexes.length];
      for (int j = 0; j < dimensionIndexes.length; j++) {
        adjacentCoord[j] = dimensionIndexes[j] + delta[j] - 1;
      }
      Coordinate adjacentCoordinate = new Coordinate(adjacentCoord);
      if (!adjacentCoordinate.equals(this)) {
        adjacentList.add(adjacentCoordinate);
      }
    }

    return adjacentList;
  }

  private void setDelta(int[] delta, int value) {
    int base = 3;
    int di = 0;
    do {
      delta[di] = value % base;
      di++;
      value /= base;
    } while (value > 0);
  }
}
