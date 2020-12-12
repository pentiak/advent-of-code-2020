package com.adventofcode.day12;

public class VehicleWaypointControlPanel extends ControlPanel<Vehicle> {

  private int waypointX;
  private int waypointY;

  public VehicleWaypointControlPanel(Vehicle vehicle) {
    super(vehicle);
    controls.put("N", v -> waypointY += v);
    controls.put("S", v -> waypointY -= v);
    controls.put("E", v -> waypointX += v);
    controls.put("W", v -> waypointX -= v);
    controls.put("F", this::moveVehicleToWaypoint);
    controls.put("R", this::rotateWaypointRight);
    controls.put("L", this::rotateWaypointLeft);
  }

  private void rotateWaypointLeft(int degrees) {
    int times = degrees / 90;
    for (int i = 0; i < times; i++) {
      int tempWaypointX = waypointX;
      waypointX = -waypointY;
      waypointY = tempWaypointX;
    }
  }

  private void rotateWaypointRight(int degrees) {
    int times = degrees / 90;
    for (int i = 0; i < times; i++) {
      int tempWaypointX = waypointX;
      waypointX = waypointY;
      waypointY = -tempWaypointX;
    }
  }

  private void moveVehicleToWaypoint(int times) {
    for (int i = 0; i < times; i++) {
      vehicle.moveNorth(waypointY);
      vehicle.moveEast(waypointX);
    }
  }


}
