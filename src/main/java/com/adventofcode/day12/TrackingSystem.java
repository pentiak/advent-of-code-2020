package com.adventofcode.day12;

import lombok.extern.log4j.Log4j2;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Log4j2
public class TrackingSystem {

  private final Map<String, TrackingData> registry = new HashMap<>();

  public String track(Trackable vehicle) {
    String id = UUID.randomUUID().toString();
    vehicle.addMovementListener(new TrackingListener(id));
    return id;
  }

  public int manhattanDistanceFrom(String trackId, int x, int y) {
    TrackingData data = registry.get(trackId);
    return Math.abs(x + data.getX()) + Math.abs(y + data.getY());
  }

  private class TrackingListener implements MovementListener {

    private final TrackingData data;

    public TrackingListener(String id) {
      this.data = registry.computeIfAbsent(id, k -> new TrackingData());
    }

    @Override
    public void turnedLeft(int degrees) {
      data.left(degrees);
    }

    @Override
    public void turnedRight(int degrees) {
      data.right(degrees);
    }

    @Override
    public void movedForward(int distance) {
      switch (data.getTurn()) {
        case 0:
          data.addY(distance);
          break;
        case 90:
          data.addX(distance);
          break;
        case 180:
          data.subY(distance);
          break;
        case 270:
          data.subX(distance);
          break;
        default:
          throw new IllegalArgumentException("Illegal forward movement at '" + data.getTurn() + "' turn");
      }
    }

    @Override
    public void movedNorth(int distance) {
      data.addY(distance);
    }

    @Override
    public void movedSouth(int distance) {
      data.subY(distance);
    }

    @Override
    public void movedEast(int distance) {
      data.addX(distance);
    }

    @Override
    public void movedWest(int distance) {
      data.subX(distance);
    }
  }
}
