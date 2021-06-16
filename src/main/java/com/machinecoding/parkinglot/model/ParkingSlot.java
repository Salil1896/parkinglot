package com.machinecoding.parkinglot.model;

public class ParkingSlot {

    private Integer level;
    private Integer slotNumber;
    private Vehicle vehicle;

    public ParkingSlot(Integer slotNumber, Integer level) {
        this.level = level;
        this.slotNumber = slotNumber;
        this.vehicle = null;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public Boolean isSlotAvailable() {
        return getVehicle() == null;
    }

    public void freeParkingSpot() {
        vehicle = null;
    }

    public Boolean parkVehicle(Vehicle v) {
        if (isSlotAvailable()) {
            vehicle = v;
            return true;
        }
        return false;
    }
}