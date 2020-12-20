package com.adventofcode.day19;


public interface RegularExpression {

  String getExpressionString();

  MatchResult matches(MessageReader reader);

  MatchResult matches(MessageReader reader, int resultIndex, int mark);
}
