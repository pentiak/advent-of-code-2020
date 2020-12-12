package com.adventofcode.day12;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public class TrackingData {

  private int x = 0;
  private int y = 0;
  private int turn = 0;

  private void normalizeDegrees() {
    turn = ((turn % 360) + 360) % 360;
  }

  public void left(int degrees) {
    turn -= degrees;
    normalizeDegrees();
  }

  public void right(int degrees) {
    turn += degrees;
    normalizeDegrees();
  }

  public void addY(int distance) {
    y += distance;
  }

  public void addX(int distance) {
    x += distance;
  }

  public void subY(int distance) {
    y -= distance;
  }

  public void subX(int distance) {
    x -= distance;
  }
}
