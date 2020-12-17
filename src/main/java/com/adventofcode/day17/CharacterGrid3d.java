package com.adventofcode.day17;

import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.objects.Object2CharMap;
import it.unimi.dsi.fastutil.objects.Object2CharOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import java.util.ArrayList;
import java.util.List;

public class CharacterGrid3d {

  private final Object2CharMap<Coord3d> map = new Object2CharOpenHashMap<>();
  private int minX = 0;
  private int maxX = 0;
  private int minY = 0;
  private int maxY = 0;
  private int minZ = 0;
  private int maxZ = 0;

  public CharacterGrid3d(char missingValue) {
    map.defaultReturnValue(missingValue);
  }

  public char setValue(Coord3d coord, char value) {
    setPrintWindowRanges(coord, value);
    if (value != map.defaultReturnValue() || map.containsKey(coord)) {
      return map.put(coord, value);
    }
    return map.defaultReturnValue();
  }

  private void setPrintWindowRanges(Coord3d coord, char value) {
    if (value != map.defaultReturnValue()) {
      int newX = coord.getX();
      if (newX < minX) {
        minX = newX;
      }

      if (newX > maxX) {
        maxX = newX;
      }

      int newY = coord.getY();
      if (newY < minY) {
        minY = newY;
      }

      if (newY > maxY) {
        maxY = newY;
      }

      int newZ = coord.getZ();
      if (newZ < minZ) {
        minZ = newZ;
      }

      if (newZ > maxZ) {
        maxZ = newZ;
      }
    }
  }

  public char valueAt(Coord3d coord) {
    return map.getChar(coord);
  }

  public int countValues(char value) {
    int count = 0;
    CharIterator iterator = map.values().iterator();
    while (iterator.hasNext()) {
      char v = iterator.nextChar();
      if (value == v) {
        count++;
      }
    }
    return count;
  }

  public List<Coord3d> getAllCoordinates() {
    return new ArrayList<>(map.keySet());
  }

  public void merge(Object2CharMap<Coord3d> other) {
    ObjectSet<Object2CharMap.Entry<Coord3d>> entries = other.object2CharEntrySet();
    for (Object2CharMap.Entry<Coord3d> entry : entries) {
      char value = entry.getCharValue();
      Coord3d coord = entry.getKey();
      setValue(coord, value);
    }
  }

  public String printGrid() {
    StringBuilder sb = new StringBuilder();
    for (int z = minZ; z <= maxZ; z++) {
      sb.append("z=").append(z).append("\n");
      for (int y = minY; y <= maxY; y++) {
        for (int x = minX; x <= maxX; x++) {
          sb.append(valueAt(Coord3d.of(x, y, z)));
        }
        sb.append("\n");
      }
      sb.append("\n");
    }
    sb.append("\n");
    return sb.toString();
  }
}
