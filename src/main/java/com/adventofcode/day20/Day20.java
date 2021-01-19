package com.adventofcode.day20;

import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class Day20 {

  public static void main(String[] args) {
    part1();
    part2();
  }

  private static void part2() {
    TileLoader loader = new TileLoader("day20/input.txt");

    JurrasicJigsaw jigsaw = new JurrasicJigsaw(loader, 12);
    jigsaw.findMonsters(new Template("day20/monster.txt", '#'));
  }

  private static void part1() {
    TileLoader loader = new TileLoader("day20/input.txt");
    JurrasicJigsaw jigsaw = new JurrasicJigsaw(loader, 12);
    jigsaw.reassemblePuzzle();
    List<Tile> cornerTiles = jigsaw.getCornerTiles();
    long product = cornerTiles.stream().mapToLong(Tile::getTileId).reduce(1L, (i1, i2) -> i1 * i2);
    log.info("Corner ids multiplied: {}", product);
  }
}
