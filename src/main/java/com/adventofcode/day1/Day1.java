package com.adventofcode.day1;

import java.nio.file.Paths;
import java.util.List;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day1 {

  public static void main(String[] args) throws Exception {
    ArgumentsFinder finder = new ArgumentsFinder(
        Paths.get(Day1.class.getClassLoader().getResource("day1/input.txt").toURI()));

    List<Integer> arguments2 = finder.findSumOfTwoArguments(2020);
    log.info(arguments2);
    log.info(arguments2.get(0) * arguments2.get(1));

    List<Integer> arguments3 = finder.findSumOfThreeArguments(2020);
    log.info(arguments3);
    log.info(arguments3.get(0) * arguments3.get(1) * arguments3.get(2));
  }
}
