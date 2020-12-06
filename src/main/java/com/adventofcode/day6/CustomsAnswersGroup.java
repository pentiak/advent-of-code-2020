package com.adventofcode.day6;

import it.unimi.dsi.fastutil.chars.CharOpenHashSet;
import it.unimi.dsi.fastutil.chars.CharSet;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CustomsAnswersGroup {

  private static final Pattern LINE_PATTERN = Pattern.compile("\\n");
  private final CharSet positiveAnswers = new CharOpenHashSet();
  private final CharSet unanimousAnswers;

  public CustomsAnswersGroup(String groupAnswers) {
    List<String> answers = LINE_PATTERN.splitAsStream(groupAnswers).collect(Collectors.toList());

    CharSet intersection = null;
    for (String answer : answers) {
      CharOpenHashSet answerSet = new CharOpenHashSet(answer.toCharArray());
      positiveAnswers.addAll(answerSet);
      if (intersection == null) {
        intersection = answerSet;
      } else {
        intersection.retainAll(answerSet);
      }
    }
    this.unanimousAnswers = intersection;
  }

  public int getDistinctPositiveAnswersCount() {
    return positiveAnswers.size();
  }

  public int getUnanimousAnswersCount() {
    return unanimousAnswers.size();
  }
}
