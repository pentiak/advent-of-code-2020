package com.adventofcode.day23;

import it.unimi.dsi.fastutil.ints.*;

public class Cups {

  private final Cup firstCup;
  private final int maxLabel;
  private final Int2ObjectMap<Cup> cupsMap = new Int2ObjectOpenHashMap<>();
  private Cup cutFrom;
  private Cup cutMiddle;
  private Cup cutTo;
  private Cup currentCup;

  public Cups(String input) {

    char[] chars = input.toCharArray();

    firstCup = new Cup(parseLabel(chars[0]), null);
    cupsMap.put(firstCup.getLabel(), firstCup);
    Cup previousCup = firstCup;
    int max = firstCup.getLabel();

    for (int i = 1; i < chars.length; i++) {
      int label = parseLabel(chars[i]);
      Cup cup = new Cup(label, firstCup);
      cupsMap.put(label, cup);
      previousCup.linkTo(cup);
      previousCup = cup;
      if (label > max) {
        max = label;
      }
    }

    maxLabel = max;
    currentCup = firstCup;
  }

  public Cups(String input, int maxLabel) {
    char[] chars = input.toCharArray();

    this.firstCup = new Cup(parseLabel(chars[0]), null);
    cupsMap.put(firstCup.getLabel(), firstCup);
    Cup previousCup = firstCup;
    int max = firstCup.getLabel();

    for (int i = 1; i < chars.length; i++) {
      int label = parseLabel(chars[i]);
      Cup cup = new Cup(label, firstCup);
      cupsMap.put(label, cup);
      previousCup.linkTo(cup);
      previousCup = cup;
      if (label > max) {
        max = label;
      }
    }

    for (int i = 0; i < maxLabel - chars.length; i++) {
      int label = max + i + 1;
      Cup cup = new Cup(label, firstCup);
      cupsMap.put(label, cup);
      previousCup.linkTo(cup);
      previousCup = cup;
    }
    this.maxLabel = maxLabel;
    this.currentCup = firstCup;
  }

  private int parseLabel(char aChar) {
    return aChar - '0';
  }

  public void move() {
    cutFrom = currentCup.getNextCup();
    cutMiddle = cutFrom.getNextCup();
    cutTo = cutMiddle.getNextCup();
    int destinationLabel = calculateDestinationLabel();

    currentCup.linkTo(cutTo.getNextCup());
    Cup destinationCup = cupsMap.get(destinationLabel);
    Cup afterDestinationCup = destinationCup.getNextCup();
    destinationCup.linkTo(cutFrom);
    cutTo.linkTo(afterDestinationCup);

    currentCup = currentCup.getNextCup();
  }

  private int calculateDestinationLabel() {
    int destinationLabel = currentCup.getLabel();
    while (true) {
      destinationLabel = destinationLabel - 1;
      if (destinationLabel <= 0) {
        destinationLabel = maxLabel;
      }

      if (destinationLabel != cutFrom.getLabel()
          && destinationLabel != cutMiddle.getLabel()
          && destinationLabel != cutTo.getLabel()) {
        return destinationLabel;
      }
    }
  }

  public Cup getCup(int label) {
    return cupsMap.get(label);
  }
}
