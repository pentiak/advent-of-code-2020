package com.adventofcode.day17;

import it.unimi.dsi.fastutil.chars.CharIterator;
import it.unimi.dsi.fastutil.objects.Object2CharMap;
import it.unimi.dsi.fastutil.objects.Object2CharOpenHashMap;
import it.unimi.dsi.fastutil.objects.ObjectSet;

import java.util.ArrayList;
import java.util.List;

public class CoordinateGrid {

  private final Object2CharMap<Coordinate> map = new Object2CharOpenHashMap<>();

  public CoordinateGrid(char missingValue) {
    map.defaultReturnValue(missingValue);
  }

  public void setValue(Coordinate coord, char value) {
    if (value != map.defaultReturnValue() || map.containsKey(coord)) {
      map.put(coord, value);
    }
  }

  public char valueAt(Coordinate coord) {
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

  public List<Coordinate> getAllCoordinates() {
    return new ArrayList<>(map.keySet());
  }

  public void merge(Object2CharMap<Coordinate> other) {
    ObjectSet<Object2CharMap.Entry<Coordinate>> entries = other.object2CharEntrySet();
    for (Object2CharMap.Entry<Coordinate> entry : entries) {
      char value = entry.getCharValue();
      Coordinate coord = entry.getKey();
      setValue(coord, value);
    }
  }
}
