package com.adventofcode.day17;

import it.unimi.dsi.fastutil.objects.Object2CharMap;
import it.unimi.dsi.fastutil.objects.Object2CharOpenHashMap;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EnergySource {

  private final CoordinateGrid grid;

  public EnergySource(CoordinateGrid grid) {
    this.grid = grid;
  }


  public void cycle() {
    List<Coordinate> coordinatesInMap = grid.getAllCoordinates();
    Set<Coordinate> coordsToCheck = new HashSet<>(coordinatesInMap);
    for (Coordinate coord : coordinatesInMap) {
      coordsToCheck.addAll(coord.getAdjacentCoords());
    }

    Object2CharMap<Coordinate> map = new Object2CharOpenHashMap<>();

    for (Coordinate coord : coordsToCheck) {
      char newValue = determineNewValue(coord);
      map.put(coord, newValue);
    }

    grid.merge(map);

  }

  private char determineNewValue(Coordinate coord) {
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
