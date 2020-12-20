package com.adventofcode.day19;

import com.adventofcode.utils.InputUtils;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import lombok.extern.log4j.Log4j2;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
public class Day19 {
  public static void main(String[] args) throws Exception {
    part1();
    part2Hack();
  }

  private static void part1() throws ParseException {
    List<String> input = InputUtils.readInputSplitByBlankLine("day19/input.txt");

    SatelliteDecoder decoder = new SatelliteDecoder(new ByteArrayInputStream(input.get(0).getBytes()));
    Int2ObjectMap<RegularExpression> registry = decoder.getRegistry();
    String patternString = registry.get(0).getExpressionString();
    log.info("Pattern: {}", patternString);
    Pattern validatePattern = Pattern.compile(patternString);
    int count = 0;
    for (String line : input.get(1).split("\\n")) {
      Matcher matcher = validatePattern.matcher(line);
      boolean matches = matcher.matches();
      if (matches) {
        count++;
      }
      log.info("Valid: {}, line: {}", matches, line);
    }
    log.info("Valid messages: {}", count);
  }

  private static void part2Hack() throws ParseException {
    List<String> input = InputUtils.readInputSplitByBlankLine("day19/input.txt");

    SatelliteDecoder decoder = new SatelliteDecoder(new ByteArrayInputStream(input.get(0).getBytes()));
    Int2ObjectMap<RegularExpression> registry = decoder.getRegistry();
    registry.put(8, new HackExpression("(" + registry.get(42).getExpressionString() + ")+"));
    registry.put(11, new HackExpression(
        registry.get(42).getExpressionString() + "(" +
            registry.get(42).getExpressionString() + "(" +
            registry.get(42).getExpressionString() + "(" +
            registry.get(42).getExpressionString() + registry.get(31).getExpressionString() + ")?" +
            registry.get(31).getExpressionString() + ")?" +
            registry.get(31).getExpressionString() + ")?" +
            registry.get(31).getExpressionString()
    ));
    String patternString = registry.get(0).getExpressionString();
    log.info("Pattern: {}", patternString);
    Pattern validatePattern = Pattern.compile(patternString);
    int count = 0;
    for (String line : input.get(1).split("\\n")) {
      Matcher matcher = validatePattern.matcher(line);
      boolean matches = matcher.matches();
      if (matches) {
        count++;
      }
      log.info("Valid: {}, line: {}", matches, line);
    }
    log.info("Valid messages: {}", count);
  }

}
