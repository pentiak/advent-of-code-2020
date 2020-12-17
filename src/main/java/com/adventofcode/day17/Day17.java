package com.adventofcode.day17;

import com.adventofcode.tools.InputLoader;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class Day17 {

  public static void main(String[] args) {
    CharacterGrid3d grid = new CharacterGrid3d('.');
    initializeGrid(grid, "day17/input.txt");

    EnergySource energySource = new EnergySource(grid);
    log.info("Before any cycles:\n\n{}", grid.printGrid());
    for (int i = 1; i <= 6; i++) {
      energySource.cycle();
      log.info("After {} cycle:\n\n{}", i, grid.printGrid());
    }

    log.info("Active cubes: {}", grid.countValues('#'));
  }

  private static void initializeGrid(CharacterGrid3d grid, String inputFile) {
    List<String> lines = InputLoader.readInputLines(inputFile);
    for (int y = 0; y < lines.size(); y++) {
      char[] values = lines.get(y).toCharArray();
      for (int x = 0; x < values.length; x++) {
        grid.setValue(Coord3d.of(x, y, 0), values[x]);
      }
    }
  }
}
