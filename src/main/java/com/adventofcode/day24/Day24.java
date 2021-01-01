package com.adventofcode.day24;

import com.adventofcode.utils.InputUtils;
import lombok.extern.log4j.Log4j2;

import java.nio.file.Files;

@Log4j2
public class Day24 {

  public static void main(String[] args) throws Exception {
    Floor floor = new Floor();
    FlipTilesRegistry registry = new FlipTilesRegistry(Files.newBufferedReader(InputUtils.resourcePath("day24/input.txt")));
    registry.setFloor(floor);
    registry.flipTiles();
    log.info(floor.countFlippedTiles());
    for (int i = 0; i < 100; i++) {
      floor.flipDay();
    }
    log.info(floor.countFlippedTiles());
  }
}
