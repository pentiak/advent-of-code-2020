package com.adventofcode.day14;

import com.adventofcode.tools.InputLoader;

import java.math.BigInteger;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Decoder {
  private static final Pattern SPLIT_LINE_PATTERN = Pattern.compile(" = ");
  private static final Pattern MEMSET_PATTERN = Pattern.compile("mem\\[(\\d+)]");
  protected final Map<BigInteger, BigInteger> memory;
  protected char[] mask;

  public Decoder(Map<BigInteger, BigInteger> memory) {
    this.memory = memory;
  }

  public void run(String inputFile) {
    InputLoader.inputLines(inputFile).forEach(this::runLine);
  }

  public void runLine(String line) {
    String[] split = SPLIT_LINE_PATTERN.split(line);
    if ("mask".equals(split[0])) {
      runMaskDirective(split[1]);
    } else if (split[0].startsWith("mem")) {
      Matcher matcher = MEMSET_PATTERN.matcher(split[0]);
      if (matcher.find()) {
        runMemsetDirective(new BigInteger(matcher.group(1)), new BigInteger(split[1]));
      }
    }
  }

  protected abstract void runMemsetDirective(BigInteger address, BigInteger value);

  private void runMaskDirective(String mask) {
    this.mask = mask.toCharArray();
  }
}
