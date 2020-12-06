package com.adventofcode.day1;

import com.adventofcode.tools.InputLoader;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class Day1 {

  public static void main(String[] args) {
    ArgumentsFinder finder = new ArgumentsFinder(InputLoader.resourcePath("day1/input.txt"));

    List<Integer> arguments2 = finder.findSumOfTwoArguments(2020);
    log.info(arguments2);
    log.info(arguments2.get(0) * arguments2.get(1));

    List<Integer> arguments3 = finder.findSumOfThreeArguments(2020);
    log.info(arguments3);
    log.info(arguments3.get(0) * arguments3.get(1) * arguments3.get(2));
  }
}
