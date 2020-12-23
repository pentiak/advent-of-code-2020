package com.adventofcode.day20;

import com.adventofcode.utils.InputUtils;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TileLoader {

  private static final Pattern TILE_HEADER = Pattern.compile("Tile\\s+(\\d+):");

  private final String inputFile;

  private int tileId;
  private char[][] content;
  private int row;
  private List<Tile> tiles;

  public TileLoader(String inputFile) {
    this.inputFile = inputFile;
  }


  public List<Tile> load() {
    tiles = new ArrayList<>();
    InputUtils.inputLines(inputFile).forEach(this::interpretLine);
    return tiles;
  }

  private void interpretLine(String line) {
    Matcher headerMatcher = TILE_HEADER.matcher(line);
    if (headerMatcher.matches()) {
      interpretHeader(headerMatcher);
    } else if (Strings.isNullOrEmpty(line)) {
      createTile();
    } else {
      addRowLine(line);
    }
  }

  private void addRowLine(String line) {
    content[row++] = line.toCharArray();
  }

  private void createTile() {
    tiles.add(new Tile(tileId, content));
  }

  private void interpretHeader(Matcher matcher) {
    tileId = Integer.parseInt(matcher.group(1));
    content = new char[10][];
    row = 0;
  }
}
