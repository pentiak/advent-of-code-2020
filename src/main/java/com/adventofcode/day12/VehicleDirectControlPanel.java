package com.adventofcode.day12;

public class VehicleDirectControlPanel extends ControlPanel<Vehicle> {

  public VehicleDirectControlPanel(Vehicle vehicle) {
    super(vehicle);
    controls.put("N", vehicle::moveNorth);
    controls.put("S", vehicle::moveSouth);
    controls.put("E", vehicle::moveEast);
    controls.put("W", vehicle::moveWest);
    controls.put("F", vehicle::moveForward);
    controls.put("R", vehicle::turnRight);
    controls.put("L", vehicle::turnLeft);
  }

}
