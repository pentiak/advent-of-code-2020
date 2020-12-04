package com.adventofcode.day3;

import com.adventofcode.tools.InputLoader;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day3 {

  public static void main(String[] args) {
    CharacterGrid grid = new HorizontallyRepeatingGrid(new FileSourcedGrid(InputLoader.resourcePath("day3/input.txt")));

    List<SlopeTraverser> traversers = List.of(
        new SlopeTraverser(grid, 1, 1),
        new SlopeTraverser(grid, 3, 1),
        new SlopeTraverser(grid, 5, 1),
        new SlopeTraverser(grid, 7, 1),
        new SlopeTraverser(grid, 1, 2)
    );

    long multipliedTrees = 1;
    for (SlopeTraverser traverser : traversers) {
      traverser.traverse();
      log.info("{}: {}", traverser, traverser.getTreesMet());
      multipliedTrees *= traverser.getTreesMet();
    }

    log.info("Multiplied trees: {}", multipliedTrees);
  }

}
