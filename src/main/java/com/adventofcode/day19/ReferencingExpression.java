package com.adventofcode.day19;

import com.google.common.base.MoreObjects;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntList;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class ReferencingExpression implements RegularExpression {
  private final Int2ObjectMap<RegularExpression> expressionRegistry;
  private final IntList references;

  public ReferencingExpression(Int2ObjectMap<RegularExpression> expressionRegistry, IntList references) {
    this.references = references;
    this.expressionRegistry = expressionRegistry;
  }


  @Override
  public String getExpressionString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < references.size(); i++) {
      sb.append(expressionRegistry.get(references.getInt(i)).getExpressionString());
    }
    return sb.toString();
  }

  @Override
  public MatchResult matches(MessageReader reader) {

    int mark = reader.getNextValIndex();

    int matchedLength = 0;

    Int2ObjectMap<MatchResult> intermediateResults = new Int2ObjectOpenHashMap<>();

    for (int i = 0; i < references.size(); i++) {
      RegularExpression expression = expressionRegistry.get(references.getInt(i));
      MatchResult result = expression.matches(reader);
      intermediateResults.put(i, result);
      while (!result.isMatch() && result.getResultIndex() < result.getResultCount() - 1) {
        result = expression.matches(reader, result.getResultIndex() + 1, result.getMark());
        intermediateResults.put(i, result);
      }

      if (!result.isMatch()) {

        MatchResult previousResult = intermediateResults.get(i - 1);
        if (previousResult != null && previousResult.getResultIndex() < previousResult.getResultCount() - 1) {
          intermediateResults.put(i - 1,
              expressionRegistry.get(references.getInt(i - 1)).matches(reader, previousResult.getResultIndex() + 1,
                  previousResult.getMark()));
          i--;
          continue;
        }

        reader.setNextValIndex(mark);
        return new MatchResult(false, 0, 0, 1, mark);
      } else {
        matchedLength += result.getMatchedLength();
      }
    }
    return new MatchResult(true, matchedLength, 0, 1, mark);
  }

  @Override
  public MatchResult matches(MessageReader reader, int resultIndex, int mark) {
    throw new IllegalStateException("Literal expression has no alternatives");
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("references", references)
        .toString();
  }
}
