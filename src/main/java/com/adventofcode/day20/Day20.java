package com.adventofcode.day20;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day20 {

  public static void main(String[] args) {
    TileLoader loader = new TileLoader("day20/input.txt");

    JurrasicJigsaw jigsaw = new JurrasicJigsaw(loader, 12);
    jigsaw.findMonsters(new Template("day20/monster.txt", '#'));

  }
}
