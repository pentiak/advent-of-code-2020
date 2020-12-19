package com.adventofcode.day1;

import com.adventofcode.utils.InputUtils;
import it.unimi.dsi.fastutil.longs.LongList;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day1 {

  public static void main(String[] args) {
    LongList input = InputUtils.inputLongList("day1/input.txt");

    LongList arguments2 = ArgumentsFinder.findSumOfTwoArguments(input, 2020);
    log.info(arguments2);
    log.info(arguments2.getLong(0) * arguments2.getLong(1));

    LongList arguments3 = ArgumentsFinder.findSumOfThreeArguments(input, 2020);
    log.info(arguments3);
    log.info(arguments3.getLong(0) * arguments3.getLong(1) * arguments3.getLong(2));
  }
}
