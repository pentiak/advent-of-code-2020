package com.adventofcode.day19;

public class HackExpression implements RegularExpression {

  private final String expressionString;

  public HackExpression(String expressionString) {
    this.expressionString = expressionString;
  }

  @Override
  public String getExpressionString() {
    return expressionString;
  }

  @Override
  public MatchResult matches(MessageReader reader) {
    return null;
  }

  @Override
  public MatchResult matches(MessageReader reader, int resultIndex, int mark) {
    return null;
  }
}
