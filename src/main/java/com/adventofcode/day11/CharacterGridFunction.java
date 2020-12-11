package com.adventofcode.day11;

import com.adventofcode.grid.CharacterGrid;

@FunctionalInterface
public interface CharacterGridFunction {

  char apply(CharacterGrid grid, int x, int y);
}
