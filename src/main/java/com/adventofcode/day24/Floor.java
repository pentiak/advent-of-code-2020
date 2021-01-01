package com.adventofcode.day24;

import it.unimi.dsi.fastutil.booleans.BooleanCollection;
import it.unimi.dsi.fastutil.booleans.BooleanIterator;
import it.unimi.dsi.fastutil.ints.IntIntPair;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;

import java.util.HashSet;
import java.util.Set;

public class Floor {

  private final Object2BooleanMap<IntIntPair> tiles = new Object2BooleanOpenHashMap<>();

  public Floor() {
    tiles.defaultReturnValue(false);
  }

  private Set<IntIntPair> getNeighbours(IntIntPair tile) {
    Set<IntIntPair> neighbours = new HashSet<>(6);

    neighbours.add(IntIntPair.of(tile.firstInt() + 2, tile.secondInt())); //E
    neighbours.add(IntIntPair.of(tile.firstInt() + 1, tile.secondInt() + 2)); //SE
    neighbours.add(IntIntPair.of(tile.firstInt() - 1, tile.secondInt() + 2)); //SW
    neighbours.add(IntIntPair.of(tile.firstInt() - 2, tile.secondInt())); //W
    neighbours.add(IntIntPair.of(tile.firstInt() - 1, tile.secondInt() - 2)); //NW
    neighbours.add(IntIntPair.of(tile.firstInt() + 1, tile.secondInt() - 2)); //NE

    return neighbours;
  }

  public void flipDay() {
    Set<IntIntPair> tilesToConsider = new HashSet<>(tiles.keySet());
    tiles.keySet().forEach(tileKey -> tilesToConsider.addAll(getNeighbours(tileKey)));

    Set<IntIntPair> tilesToFlip = new HashSet<>();
    for (IntIntPair tile : tilesToConsider) {
      int flippedNeighbours = countFlippedNeighbours(tile);

      if (tiles.getBoolean(tile)) {
        if (flippedNeighbours == 0 || flippedNeighbours > 2) {
          tilesToFlip.add(tile);
        }
      } else {
        if (flippedNeighbours == 2) {
          tilesToFlip.add(tile);
        }
      }
    }

    tilesToFlip.forEach(this::flipTile);
  }

  private int countFlippedNeighbours(IntIntPair tile) {
    return (int) getNeighbours(tile).stream().filter(tiles::getBoolean).count();
  }

  public void flipTile(int x, int y) {
    flipTile(IntIntPair.of(x, y));
  }

  private void flipTile(IntIntPair tile) {
    tiles.put(tile, !tiles.getBoolean(tile));
  }

  public int countFlippedTiles() {
    BooleanCollection values = tiles.values();
    BooleanIterator iterator = values.iterator();
    int flippedTiles = 0;
    while (iterator.hasNext()) {
      if (iterator.nextBoolean()) {
        flippedTiles++;
      }
    }
    return flippedTiles;
  }
}
