package com.adventofcode.day23;

import com.google.common.base.MoreObjects;

public class Cup {
  private final int label;
  private Cup nextCup;

  public Cup(int label, Cup nextCup) {
    this.label = label;
    this.nextCup = nextCup;
  }

  public void linkTo(Cup nextCup) {
    this.nextCup = nextCup;
  }

  public int getLabel() {
    return label;
  }

  public Cup getNextCup() {
    return nextCup;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("label", label)
        .toString();
  }
}
