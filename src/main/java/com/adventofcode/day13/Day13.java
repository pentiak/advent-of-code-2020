package com.adventofcode.day13;

import com.adventofcode.tools.InputLoader;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.longs.Long2LongLinkedOpenHashMap;
import lombok.extern.log4j.Log4j2;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

@Log4j2
public class Day13 {

  public static void main(String[] args) {
    part1();
    part2();
  }

  private static void part1() {
    List<String> lines = InputLoader.readInputLines("day13/input.txt");
    int arriveTime = Integer.parseInt(lines.get(0));
    String[] split = lines.get(1).split(",");
    IntList workingBuses = Arrays.stream(split)
        .filter(v -> !"x".equals(v))
        .mapToInt(Integer::parseInt)
        .collect(IntArrayList::new, IntArrayList::add, IntArrayList::addAll);

    int shortestWaitTime = Integer.MAX_VALUE;
    int shortestWaitTimeBusId = -1;
    for (int i = 0; i < workingBuses.size(); i++) {
      int busId = workingBuses.getInt(i);
      int quotient = arriveTime / busId;
      int remainder = arriveTime % busId;
      int multiply = quotient;
      if (remainder > 0) {
        multiply++;
      }
      int waitTime = multiply * busId - arriveTime;
      if (waitTime < shortestWaitTime) {
        shortestWaitTime = waitTime;
        shortestWaitTimeBusId = busId;
      }
    }
    log.info("Part1 result: {}", shortestWaitTimeBusId * shortestWaitTime);
  }

  private static void part2() {
    List<String> lines = InputLoader.readInputLines("day13/input.txt");
    String[] split = lines.get(1).split(",");
    Long2LongLinkedOpenHashMap modulos = new Long2LongLinkedOpenHashMap();
    for (int i = 0; i < split.length; i++) {
      String val = split[i];
      if ("x".equals(val)) {
        continue;
      }
      int id = Integer.parseInt(val);
      modulos.put(id, (id - i) % id);
    }
    long[] ids = modulos.keySet().toLongArray();
    long[] mods = modulos.values().toLongArray();


    log.info("Part 2 result: {}", chineseRemainderTheorem(ids, mods));
  }

  public static long chineseRemainderTheorem(long[] numbers, long[] mods) {

    long product = Arrays.stream(numbers).reduce(1, (i, j) -> i * j);

    long partialProduct;
    long finalSum = 0;
    for (int i = 0; i < numbers.length; i++) {
      partialProduct = product / numbers[i];
      finalSum += mods[i] * BigInteger.valueOf(partialProduct).modInverse(
          BigInteger.valueOf(numbers[i])).longValue() * partialProduct;
    }
    return finalSum % product;
  }
}
