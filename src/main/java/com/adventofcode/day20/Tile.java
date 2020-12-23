package com.adventofcode.day20;

import com.google.common.base.MoreObjects;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class Tile {

  private final int tileId;
  private Set<String> edges;
  private char[][] content;

  public Tile(int tileId, char[][] content) {
    this.tileId = tileId;
    this.content = content;
    this.edges = calculateEdges();
  }

  public char[][] getContent() {
    return content;
  }

  private Set<String> calculateEdges() {
    Set<String> edg = new HashSet<>();

    String top = getTopEdge();
    edg.add(top);
    edg.add(StringUtils.reverse(top));


    String bottom = getBottomEdge();
    edg.add(bottom);
    edg.add(StringUtils.reverse(bottom));

    String left = getLeftEdge();
    edg.add(left);
    edg.add(StringUtils.reverse(left));

    String right = getRightEdge();
    edg.add(right);
    edg.add(StringUtils.reverse(right));

    return edg;
  }

  public String getTopEdge() {
    return new String(content[0]);
  }

  public String getBottomEdge() {
    return new String(content[content.length - 1]);
  }

  public String getRightEdge() {
    char[] edge = new char[content.length];
    for (int i = 0; i < content.length; i++) {
      edge[i] = content[i][content[i].length - 1];
    }
    return new String(edge);
  }

  public String getLeftEdge() {
    char[] edge = new char[content.length];
    for (int i = 0; i < content.length; i++) {
      edge[i] = content[i][0];
    }
    return new String(edge);
  }

  public void rotateRight() {
    this.content = ArrayUtils.rotateRight(content);
  }

  public void flipHorizontal() {
    this.content = ArrayUtils.flipHorizontal(content);
  }

  public void flipVertical() {
    this.content = ArrayUtils.flipVertical(content);
  }

  public void trimEdges() {
    char[][] newContent = new char[content.length - 2][];
    for (int row = 1; row < content.length - 1; row++) {
      newContent[row - 1] = Arrays.copyOfRange(content[row], 1, content[row].length - 1);
    }
    this.content = newContent;
    this.edges = Collections.emptySet();
  }

  public int getTileId() {
    return tileId;
  }

  public Set<String> getEdges() {
    return edges;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Tile tile = (Tile) o;
    return tileId == tile.tileId;
  }

  @Override
  public int hashCode() {
    return Objects.hash(tileId);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("tileId", tileId)
        .toString();
  }
}
