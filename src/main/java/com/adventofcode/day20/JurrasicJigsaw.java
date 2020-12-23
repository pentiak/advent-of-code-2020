package com.adventofcode.day20;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import lombok.extern.log4j.Log4j2;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Log4j2
public class JurrasicJigsaw {

  public final int puzzleSize;
  private final TileLoader loader;
  private SetMultimap<Tile, Tile> neighbours;
  private Tile[][] puzzle;

  public JurrasicJigsaw(TileLoader loader, int puzzleSize) {
    this.loader = loader;
    this.puzzleSize = puzzleSize;
  }

  public void reassemblePuzzle() {
    resetPuzzle();
    findNeighbours();
    putFirstCorner();
    solvePuzzle();
    log.info(Arrays.deepToString(puzzle));
  }

  private void solvePuzzle() {
    for (int row = 0; row < puzzle.length; row++) {
      for (int col = 0; col < puzzle[row].length; col++) {
        if (row == 0 && col == 0) {
          continue;
        }

        Tile leftTile = col > 0 ? puzzle[row][col - 1] : null;
        Tile topTile = row > 0 ? puzzle[row - 1][col] : null;
        Set<Tile> leftCandidates = neighbours.get(leftTile);
        Set<Tile> topCandidates = neighbours.get(topTile);
        Set<Tile> candidates;
        if (leftCandidates.isEmpty()) {
          candidates = topCandidates;
        } else if (topCandidates.isEmpty()) {
          candidates = leftCandidates;
        } else {
          candidates = Sets.intersection(topCandidates, leftCandidates);
        }
        boolean candidateFound = false;
        for (Tile candidate : candidates) {
          boolean fits = tryToFit(candidate, leftTile, topTile);
          if (fits) {
            puzzle[row][col] = candidate;
            candidateFound = true;
            leftCandidates.remove(candidate);
            topCandidates.remove(candidate);
            Set<Tile> candidateNeighbours = neighbours.get(candidate);
            candidateNeighbours.remove(leftTile);
            candidateNeighbours.remove(topTile);
            break;
          }
        }
        if (!candidateFound) {
          throw new IllegalStateException("Did not find a match");
        }
      }
    }
  }

  private boolean tryToFit(Tile candidate, Tile leftTile, Tile topTile) {
    for (int flips = 0; flips < 3; flips++) {
      for (int rotate = 0; rotate < 4; rotate++) {
        if (
            (leftTile == null || leftTile.getRightEdge().equals(candidate.getLeftEdge())) &&
                (topTile == null || topTile.getBottomEdge().equals(candidate.getTopEdge()))
        ) {
          return true;
        }
        candidate.rotateRight();
      }
      if (flips == 0) {
        candidate.flipVertical();
      } else if (flips == 1) {
        candidate.flipVertical();
        candidate.flipHorizontal();
      }
    }
    return false;
  }

  private void resetPuzzle() {
    puzzle = new Tile[puzzleSize][];
    for (int i = 0; i < puzzleSize; i++) {
      puzzle[i] = new Tile[puzzleSize];
    }
  }

  private void putFirstCorner() {
    Tile corner = findFirstCorner();
    puzzle[0][0] = corner;

    Set<Tile> cornerNeighbours = neighbours.get(corner);
    Tile rightTile = null;
    Tile bottomTile = null;

    while (bottomTile == null || rightTile == null) {
      corner.rotateRight();
      rightTile = findTileWithEdge(cornerNeighbours, corner.getRightEdge());
      if (rightTile != null) {
        bottomTile = findTileWithEdge(cornerNeighbours, corner.getBottomEdge());
      }
    }

    neighbours.get(rightTile).remove(corner);
    neighbours.get(bottomTile).remove(corner);

    log.info("Right tile: {}", rightTile);
    log.info("Bottom tile: {}", bottomTile);
  }

  private Tile findTileWithEdge(Set<Tile> tiles, String rightEdge) {
    for (Tile tile : tiles) {
      if (tile.getEdges().contains(rightEdge)) {
        return tile;
      }
    }
    return null;
  }

  private Tile findFirstCorner() {
    for (Tile key : neighbours.keys()) {
      if (neighbours.get(key).size() == 2) {
        return key;
      }
    }
    return null;
  }

  private void findNeighbours() {
    List<Tile> tiles = loader.load();
    neighbours = HashMultimap.create();
    Tile corner = null;

    for (int i = 0, tilesSize = tiles.size(); i < tilesSize; i++) {
      Tile tile = tiles.get(i);
      for (int j = i + 1, size = tiles.size(); j < size; j++) {
        Tile otherTile = tiles.get(j);
        Sets.SetView<String> intersection = Sets.intersection(tile.getEdges(), otherTile.getEdges());
        if (!intersection.isEmpty()) {
          neighbours.put(tile, otherTile);
          neighbours.put(otherTile, tile);
        }
      }
      if (neighbours.get(tile).size() == 2 && corner == null) {
        corner = tile;
      }
    }
  }

  public void findMonsters(Template template) {
    reassemblePuzzle();
    trimEdges();
    char[][] puzzleChars = convertToCharArray();

    int waterDots = ArrayUtils.countCharacters(puzzleChars, '#');


    int foundMonsters = 0;
    for (int flips = 0; flips < 2; flips++) {
      for (int rotate = 0; rotate < 3; rotate++) {
        foundMonsters += ArrayUtils.arrMatchTemplate(puzzleChars, template);
        puzzleChars = ArrayUtils.rotateRight(puzzleChars);
        ArrayUtils.printCharArray(puzzleChars);
        System.out.println();
      }
      if (flips == 0) {
        puzzleChars = ArrayUtils.rotateRight(puzzleChars);
        puzzleChars = ArrayUtils.flipVertical(puzzleChars);
      }
    }

    int monsterDots = template.getDots();
    int waterRoughness = ArrayUtils.countCharacters(puzzleChars, '#');

    log.info("Water dots: {}", waterDots);
    log.info("Monster dots: {}", monsterDots);
    log.info("Monsters found: {}", foundMonsters);
    log.info("Water roughness: {}", waterRoughness);
  }

  private char[][] convertToCharArray() {
    int tileRows = puzzle[0][0].getContent().length;
    int tileCols = puzzle[0][0].getContent()[0].length;
    char[][] puzzleArray = new char[tileRows * puzzleSize][];
    for (int puzzleRow = 0; puzzleRow < puzzle.length; puzzleRow++) {
      for (int puzzleCol = 0; puzzleCol < puzzle[0].length; puzzleCol++) {
        if (puzzleCol == 0) {
          for (int i = 0; i < tileRows; i++) {
            puzzleArray[puzzleRow * tileRows + i] = new char[tileCols * puzzleSize];
          }
        }
        char[][] tile = puzzle[puzzleRow][puzzleCol].getContent();
        for (int tileRow = 0; tileRow < tile.length; tileRow++) {
          for (int tileCol = 0; tileCol < tile[0].length; tileCol++) {
            puzzleArray[puzzleRow * tileRows + tileRow][puzzleCol * tileCols + tileCol] = tile[tileRow][tileCol];
          }
        }
      }
    }
    return puzzleArray;
  }

  private void trimEdges() {
    for (Tile[] tiles : puzzle) {
      for (Tile tile : tiles) {
        tile.trimEdges();
      }
    }
  }
}
