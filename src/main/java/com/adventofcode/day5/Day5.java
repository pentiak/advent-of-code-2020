package com.adventofcode.day5;

import com.adventofcode.utils.InputUtils;
import it.unimi.dsi.fastutil.ints.IntBidirectionalIterator;
import it.unimi.dsi.fastutil.ints.IntRBTreeSet;
import it.unimi.dsi.fastutil.ints.IntSortedSet;
import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.nio.file.Files;

@Log4j2
public class Day5 {
  public static void main(String[] args) throws Exception {
    BoardingPassDecoder decoder = new BoardingPassDecoder();

    try (BufferedReader reader = Files.newBufferedReader(InputUtils.resourcePath("day5/input.txt"))) {

      String line;
      IntSortedSet ids = new IntRBTreeSet();
      while ((line = reader.readLine()) != null) {
        Seat seat = decoder.decode(line);
        ids.add(seat.getId());
      }
      IntBidirectionalIterator iterator = ids.iterator();
      int expectedId = iterator.nextInt();
      while (iterator.hasNext() && ++expectedId == iterator.nextInt()) ;

      log.info("Highest seat id: {}", ids.lastInt());
      log.info("My id: {}", expectedId);
    }
  }
}
