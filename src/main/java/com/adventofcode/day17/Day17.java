package com.adventofcode.day17;

import com.adventofcode.utils.InputUtils;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class Day17 {

  public static void main(String[] args) {
    CoordinateGrid grid = new CoordinateGrid('.');
    initializeGrid(grid, "day17/input.txt");

    EnergySource energySource = new EnergySource(grid);
    for (int i = 1; i <= 6; i++) {
      energySource.cycle();
    }
    log.info("Active cubes 3-dim: {}", grid.countValues('#'));

    CoordinateGrid grid4d = new CoordinateGrid('.');
    initializeGrid4d(grid4d, "day17/input.txt");
    EnergySource energySource4d = new EnergySource(grid4d);
    for (int i = 1; i <= 6; i++) {
      energySource4d.cycle();
    }
    log.info("Active cubes 4-dim: {}", grid4d.countValues('#'));
  }

  private static void initializeGrid4d(CoordinateGrid grid, String inputFile) {
    List<String> lines = InputUtils.readInputLines(inputFile);
    for (int y = 0; y < lines.size(); y++) {
      char[] values = lines.get(y).toCharArray();
      for (int x = 0; x < values.length; x++) {
        grid.setValue(Coordinate.of(x, y, 0, 0), values[x]);
      }
    }
  }

  private static void initializeGrid(CoordinateGrid grid, String inputFile) {
    List<String> lines = InputUtils.readInputLines(inputFile);
    for (int y = 0; y < lines.size(); y++) {
      char[] values = lines.get(y).toCharArray();
      for (int x = 0; x < values.length; x++) {
        grid.setValue(Coordinate.of(x, y, 0), values[x]);
      }
    }
  }
}
