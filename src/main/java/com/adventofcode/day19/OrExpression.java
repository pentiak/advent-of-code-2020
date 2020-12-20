package com.adventofcode.day19;

import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class OrExpression implements RegularExpression {

  private final List<RegularExpression> alternativeExpressions;

  public OrExpression(List<RegularExpression> alternativeExpressions) {
    this.alternativeExpressions = alternativeExpressions;
  }

  @Override
  public String getExpressionString() {
    return alternativeExpressions.stream()
        .map(RegularExpression::getExpressionString)
        .collect(Collectors.joining("|", "(", ")"));
  }

  @Override
  public MatchResult matches(MessageReader reader) {
    int mark = reader.getNextValIndex();

    for (int alternativeIndex = 0; alternativeIndex < alternativeExpressions.size(); alternativeIndex++) {
      reader.setNextValIndex(mark);
      RegularExpression expression = alternativeExpressions.get(alternativeIndex);
      MatchResult result = expression.matches(reader);
      while (!result.isMatch() && result.getResultIndex() < result.getResultCount() - 1) {
        result = expression.matches(reader, result.getResultIndex() + 1, result.getMark());
      }

      if (result.isMatch()) {
        return new MatchResult(true, result.getMatchedLength(), alternativeIndex, alternativeExpressions.size(), mark);
      }
    }

    reader.setNextValIndex(mark);

    return new MatchResult(false, 0, alternativeExpressions.size(), alternativeExpressions.size(), mark);
  }

  @Override
  public MatchResult matches(MessageReader reader, int resultIndex, int mark) {
    for (int alternativeIndex = resultIndex; alternativeIndex < alternativeExpressions.size(); alternativeIndex++) {
      reader.setNextValIndex(mark);
      RegularExpression expression = alternativeExpressions.get(alternativeIndex);
      MatchResult result = expression.matches(reader);
      while (!result.isMatch() && result.getResultIndex() < result.getResultCount() - 1) {
        result = expression.matches(reader, result.getResultIndex() + 1, result.getMark());
      }

      if (result.isMatch()) {
        return new MatchResult(true, result.getMatchedLength(), alternativeIndex, alternativeExpressions.size(), mark);
      }
    }
    reader.setNextValIndex(mark);

    return new MatchResult(false, 0, alternativeExpressions.size(), alternativeExpressions.size(), mark);
  }
}
