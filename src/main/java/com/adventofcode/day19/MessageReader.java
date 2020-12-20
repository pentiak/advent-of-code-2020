package com.adventofcode.day19;

import java.util.Arrays;

public class MessageReader {
  private final char[] message;
  private int nextValIndex;

  public MessageReader(String message) {
    this.message = message.toCharArray();
    this.nextValIndex = 0;
  }

  public boolean hasNext() {
    return nextValIndex <= message.length - 1;
  }

  public char next() {
    if (nextValIndex == message.length) {
      throw new IllegalStateException("Already at the end of the String");
    }
    return message[nextValIndex++];
  }

  public void back() {
    if (nextValIndex == 0) {
      throw new IllegalStateException("Already at the beginning of the String");
    }
    nextValIndex--;
  }

  public int getNextValIndex() {
    return nextValIndex;
  }

  public void setNextValIndex(int nextValIndex) {
    this.nextValIndex = nextValIndex;
  }

  public String getReadPart() {
    return new String(Arrays.copyOf(message, Math.max(nextValIndex, 0)));
  }
}
