package com.adventofcode.day19;

import lombok.Getter;

@Getter
public class MatchResult {
  private final boolean match;
  private final int matchedLength;
  private final int resultIndex;
  private final int resultCount;
  private final int mark;

  public MatchResult(boolean match, int matchedLength, int resultIndex, int resultCount, int mark) {
    this.match = match;
    this.matchedLength = matchedLength;
    this.resultIndex = resultIndex;
    this.resultCount = resultCount;
    this.mark = mark;
  }
}
