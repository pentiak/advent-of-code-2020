package com.adventofcode.day19;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LiteralExpression implements RegularExpression {
  private final char literal;

  public LiteralExpression(String literal) {
    if (literal.length() > 1) {
      throw new IllegalArgumentException("Literal '" + literal + "' is longer than 1 char");
    }
    this.literal = literal.charAt(0);
  }

  @Override
  public String getExpressionString() {
    return String.valueOf(literal);
  }

  @Override
  public MatchResult matches(MessageReader reader) {
    int mark = reader.getNextValIndex();
    if (!reader.hasNext()) {
      log.info("Reader is finished already");
      return new MatchResult(true, 0, 0, 1, mark);
    }

    char read = reader.next();
    if (read == literal) {
      System.out.println(reader.getReadPart());
      return new MatchResult(true, 1, 0, 1, mark);
    } else {
      System.out.println(reader.getReadPart() + " did not match " + literal);
      reader.back();
      return new MatchResult(false, 0, 0, 1, mark);
    }
  }

  @Override
  public MatchResult matches(MessageReader reader, int resultIndex, int mark) {
    throw new IllegalStateException("Literal expression has no alternatives");
  }
}
