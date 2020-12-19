package com.adventofcode.day6;

import com.adventofcode.utils.InputUtils;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class Day6 {
  public static void main(String[] args) {
    List<CustomsAnswersGroup> answersGroups = InputUtils.inputSplitByBlankLine("day6/input.txt")
        .map(CustomsAnswersGroup::new).collect(Collectors.toList());

    int sumOfPositiveAnswers = 0;
    int sumOfUnanimousAnswers = 0;

    for (CustomsAnswersGroup answersGroup : answersGroups) {
      sumOfPositiveAnswers += answersGroup.getDistinctPositiveAnswersCount();
      sumOfUnanimousAnswers+= answersGroup.getUnanimousAnswersCount();
    }

    log.info("Sum of positive answers: {}", sumOfPositiveAnswers);
    log.info("Sum of unanimous answers: {}", sumOfUnanimousAnswers);
  }
}
