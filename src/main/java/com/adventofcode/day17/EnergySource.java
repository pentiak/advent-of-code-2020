package com.adventofcode.day17;

import it.unimi.dsi.fastutil.objects.Object2CharMap;
import it.unimi.dsi.fastutil.objects.Object2CharOpenHashMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnergySource {

  private final CharacterGrid3d grid;

  public EnergySource(CharacterGrid3d grid) {
    this.grid = grid;
  }


  public void cycle() {
    List<Coord3d> coordinatesInMap = grid.getAllCoordinates();
    Set<Coord3d> coordsToCheck = new HashSet<>(coordinatesInMap);
    for (Coord3d coord : coordinatesInMap) {
      coordsToCheck.addAll(coord.getAdjacentCoords());
    }

    Object2CharMap<Coord3d> map = new Object2CharOpenHashMap<>();

    for (Coord3d coord : coordsToCheck) {
      char newValue = determineNewValue(coord);
      map.put(coord, newValue);
    }

    grid.merge(map);

  }

  private char determineNewValue(Coord3d coord) {
    char currentValue = grid.valueAt(coord);
    long activeNeighbours = coord.getAdjacentCoords().stream().filter(c -> grid.valueAt(c) == '#').count();

    if (currentValue == '#' && activeNeighbours != 2 && activeNeighbours != 3) {
      return '.';
    } else if (currentValue == '.' && activeNeighbours == 3) {
      return '#';
    } else {
      return currentValue;
    }
  }
}
