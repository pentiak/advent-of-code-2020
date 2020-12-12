package com.adventofcode.day12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Vehicle implements Trackable {

  private final List<MovementListener> movementListeners = new ArrayList<>();

  @Override
  public void addMovementListener(MovementListener listener) {
    movementListeners.add(listener);
  }

  private void notifyOfMovement(Consumer<MovementListener> notifyAction) {
    movementListeners.forEach(notifyAction);
  }

  public void turnLeft(int degrees) {
    notifyOfMovement(listen -> listen.turnedLeft(degrees));
  }

  public void turnRight(int degrees) {
    notifyOfMovement(listen -> listen.turnedRight(degrees));
  }

  public void moveForward(int distance) {
    notifyOfMovement(listen -> listen.movedForward(distance));
  }

  public void moveNorth(int distance) {
    notifyOfMovement(listen -> listen.movedNorth(distance));
  }

  public void moveSouth(int distance) {
    notifyOfMovement(listen -> listen.movedSouth(distance));
  }

  public void moveEast(int distance) {
    notifyOfMovement(listen -> listen.movedEast(distance));
  }

  public void moveWest(int distance) {
    notifyOfMovement(listen -> listen.movedWest(distance));
  }
}
