package com.adventofcode.day18;

import com.adventofcode.utils.InputUtils;
import lombok.extern.log4j.Log4j2;

import java.nio.file.Files;
import java.nio.file.Path;


@Log4j2
public class Day18 {

  public static void main(String[] args) throws Exception {
    Path path = InputUtils.resourcePath("day18/input.txt");
    log.info("V1: {}", new Calculator(Files.newInputStream(path)).calculate());
    log.info("V2: {}", new CalculatorV2(Files.newInputStream(path)).calculate());
  }
}
