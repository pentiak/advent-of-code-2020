package com.adventofcode.day12;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day12Test {

  @Test
  void waypoint() {
    TrackingSystem gps = new TrackingSystem();
    Vehicle vehicle = new Vehicle();

    String trackId = gps.track(vehicle);

    VehicleWaypointControlPanel cp = new VehicleWaypointControlPanel(vehicle);
    cp.runInstruction("E10");
    cp.runInstruction("N1");

    cp.runInstructions("day12/input.txt");

    Assertions.assertEquals(286, gps.manhattanDistanceFrom(trackId, 0, 0));

  }
}