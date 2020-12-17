package com.adventofcode.day17;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
public class Coord3d {

  public static final Coord3d ZERO = new Coord3d(0, 0, 0);

  private final int x;
  private final int y;
  private final int z;

  private Coord3d(int x, int y, int z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public static Coord3d of(int x, int y, int z) {
    return new Coord3d(x, y, z);
  }

  public List<Coord3d> getAdjacentCoords() {
    List<Coord3d> adjacent = new ArrayList<>(26);
    for (int x = -1; x <= 1; x++) {
      for (int y = -1; y <= 1; y++) {
        for (int z = -1; z <= 1; z++) {
          if (!(x == 0 && y == 0 && z == 0)) {
            adjacent.add(Coord3d.of(this.x + x, this.y + y, this.z + z));
          }
        }
      }
    }
    return adjacent;
  }
}
