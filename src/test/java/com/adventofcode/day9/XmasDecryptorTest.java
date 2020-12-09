package com.adventofcode.day9;

import com.adventofcode.day1.ArgumentsFinder;
import com.adventofcode.tools.InputLoader;
import it.unimi.dsi.fastutil.longs.LongList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class XmasDecryptorTest {

  @Test
  void findCorruptedValue() {
    LongList input = InputLoader.inputLongList("day9/input.txt");
    XmasDecryptor decryptor = new XmasDecryptor(input, 5);
    assertEquals(127, decryptor.findCorruptedValue());
  }

  @Test
  void findSummingSublist() {
    LongList input = InputLoader.inputLongList("day9/input2.txt");
    assertFalse(ArgumentsFinder.findSummingSublist(input, 127).isEmpty());
  }
}