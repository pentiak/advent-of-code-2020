package com.adventofcode.day8;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

public class HackingSoftware implements Software {

  private final Software delegate;
  private final Int2ObjectMap<Instruction> hacks = new Int2ObjectOpenHashMap<>();


  public HackingSoftware(Software delegate) {
    this.delegate = delegate;
  }

  public void addHack(int index, Instruction hack) {
    hacks.put(index, hack);
  }

  public void removeHacks() {
    hacks.clear();
  }

  @Override
  public boolean hasInstruction(int index) {
    return delegate.hasInstruction(index) || hacks.containsKey(index);
  }

  @Override
  public Instruction getInstruction(int index) {
    Instruction hack = hacks.get(index);
    return hack != null ? hack : delegate.getInstruction(index);
  }

  @Override
  public int getNumOfInstructions() {
    return delegate.getNumOfInstructions();
  }
}
