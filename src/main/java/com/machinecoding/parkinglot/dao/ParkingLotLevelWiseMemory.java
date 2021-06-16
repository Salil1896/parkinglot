package com.machinecoding.parkinglot.dao;


import com.machinecoding.parkinglot.constants.AppConstants;
import com.machinecoding.parkinglot.model.ParkingSlot;
import com.machinecoding.parkinglot.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author salilmamodiya
 */
public class ParkingLotLevelWiseMemory {

    private Integer level;
    private Integer slots;
    private Set<Integer> freeSlot;
    private Map<Integer, ParkingSlot> slotNumberToSlotMap;

    public ParkingLotLevelWiseMemory(Integer level, Integer slots) {
        this.level = level;
        this.slots = slots;

        slotNumberToSlotMap = new ConcurrentHashMap<>();
        freeSlot = new TreeSet<>();
        for (int i = 1; i <= slots; i++) {
            freeSlot.add(i);
            slotNumberToSlotMap.put(i, new ParkingSlot(i, level));
        }
    }

    private Integer getFirstFreeSlot() {
        for (int i = 1; i <= slots; i++) {
            if (slotNumberToSlotMap.get(i).isSlotAvailable()) return slotNumberToSlotMap.get(i).getSlotNumber();
        }
        return null;
    }

    public int park(Vehicle vehicle) {

        if (freeSlot.size() == 0) return AppConstants.NOT_AVAILABLE;
        Integer freeParkingSlot = getFirstFreeSlot();

        if (freeParkingSlot != null) {
            freeSlot.add(freeParkingSlot);
            slotNumberToSlotMap.get(freeParkingSlot).parkVehicle(vehicle);
        } else {
            return AppConstants.NOT_AVAILABLE;
        }

        return freeParkingSlot;
    }

    public boolean unPark(Integer slotNumber) {
        if (!slotNumberToSlotMap.get(slotNumber).isSlotAvailable()) {
            freeSlot.add(slotNumber);
            slotNumberToSlotMap.get(slotNumber).freeParkingSpot();
            return true;
        }
        return false;
    }

    public List<String> getLevelStatus() {
        List<String> statusList = new ArrayList<>();
        for (int i = 1; i <= slots; i++) {
            if (!slotNumberToSlotMap.get(i).isSlotAvailable()) {
                Vehicle vehicle = slotNumberToSlotMap.get(i).getVehicle();
                statusList.add(i + "           " + vehicle.getRegistrationNumber() + "      " + vehicle.getColor());
            }
        }
        return statusList;

    }

    public List<String> getRegistrationNumbersForColor(String color) {

        List<String> regNumbers = new ArrayList<>();
        for (int i = 1; i <= slots; i++) {
            if (!slotNumberToSlotMap.get(i).isSlotAvailable()) {
                Vehicle vehicle = slotNumberToSlotMap.get(i).getVehicle();
                if (vehicle.getColor().equals(color)) {
                    regNumbers.add(vehicle.getRegistrationNumber());
                }
            }
        }
        return regNumbers;
    }

    public List<Integer> getSlotNumbersForColor(String color) {

        List<Integer> slotNumbers = new ArrayList<>();
        for (int i = 1; i <= slots; i++) {
            if (!slotNumberToSlotMap.get(i).isSlotAvailable()) {
                Vehicle vehicle = slotNumberToSlotMap.get(i).getVehicle();
                if (color.equals(vehicle.getColor())) {
                    slotNumbers.add(i);
                }
            }
        }
        return slotNumbers;
    }


    public Integer getSlotNumberForRegistrationNumber(String registrationNumber) {

        for (int i = 1; i <= slots; i++) {
            if (!slotNumberToSlotMap.get(i).isSlotAvailable()) {
                Vehicle vehicle = slotNumberToSlotMap.get(i).getVehicle();
                if (vehicle.getRegistrationNumber().equals(registrationNumber)) {
                    return i;
                }
            }
        }
        return AppConstants.NOT_AVAILABLE;
    }


}


