package com.adventofcode.day16;

import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntLists;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Ticket {

  private final IntList values;
  private final Set<String> fieldNames;

  public Ticket(Collection<String> fieldNames, IntList values) {
    this.fieldNames = new HashSet<>(fieldNames);
    this.values = IntLists.unmodifiable(values);
  }

  public IntList getValues() {
    return values;
  }
}
