package com.adventofcode.day12;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Day12 {

  public static void main(String[] args) {
    TrackingSystem gps = new TrackingSystem();

    Vehicle vehicle = new Vehicle();
    String trackId = gps.track(vehicle);
    vehicle.turnRight(90);
    VehicleDirectControlPanel control = new VehicleDirectControlPanel(vehicle);
    control.runInstructions("day12/input.txt");
    log.info("Manhattan distance direct: {}", gps.manhattanDistanceFrom(trackId, 0, 0));

    Vehicle vehicle2 = new Vehicle();
    String trackWaypointId = gps.track(vehicle2);
    VehicleWaypointControlPanel controlWaypoint = new VehicleWaypointControlPanel(vehicle2);
    controlWaypoint.runInstruction("E10");
    controlWaypoint.runInstruction("N1");
    controlWaypoint.runInstructions("day12/input.txt");
    log.info("Manhattan distance waypoint: {}", gps.manhattanDistanceFrom(trackWaypointId, 0, 0));
  }
}
