package com.adventofcode.day12;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrackingDataTest {

  @ParameterizedTest
  @CsvSource({
      "90,270",
      "0,0",
      "-1,1",
      "360,0",
      "180,180"
  })
  void left(int left, int expected) {
    TrackingData data = new TrackingData();
    data.left(left);
    assertEquals(expected, data.getTurn());
  }
}