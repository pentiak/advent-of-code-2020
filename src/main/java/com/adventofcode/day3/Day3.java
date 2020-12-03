package com.adventofcode.day3;

import com.adventofcode.tools.InputLoader;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day3 {

  public static void main(String[] args) {
    CharacterGrid grid = new HorizontallyRepeatingGrid(InputLoader.resourcePath("day3/input.txt"));

    SlopeTraverser st11 = new SlopeTraverser(grid, 1, 1);
    SlopeTraverser st31 = new SlopeTraverser(grid, 3, 1);
    SlopeTraverser st51 = new SlopeTraverser(grid, 5, 1);
    SlopeTraverser st71 = new SlopeTraverser(grid, 7, 1);
    SlopeTraverser st12 = new SlopeTraverser(grid, 1, 2);

    st11.traverse();
    log.info("{}: {}", st11, st11.getTreesMet());
    st31.traverse();
    log.info("{}: {}", st31, st31.getTreesMet());
    st51.traverse();
    log.info("{}: {}", st51, st51.getTreesMet());
    st71.traverse();
    log.info("{}: {}", st71, st71.getTreesMet());
    st12.traverse();
    log.info("{}: {}", st12, st12.getTreesMet());

    log.info("Multiplied trees: {}",
        (long)st11.getTreesMet() * st31.getTreesMet() * st51.getTreesMet() * st71.getTreesMet() * st12.getTreesMet());
  }

}
