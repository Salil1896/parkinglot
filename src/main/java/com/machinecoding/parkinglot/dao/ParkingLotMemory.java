package com.machinecoding.parkinglot.dao;

import com.machinecoding.parkinglot.model.Vehicle;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author salilmamodiya
 */
public class ParkingLotMemory {

    private Map<Integer, ParkingLotLevelWiseMemory> levelMemoryMap;
    private Integer levels;
    private static ParkingLotMemory instance = null;

    public static ParkingLotMemory getInstance(Integer levels, List<Integer> slotCounts) {
        if (instance == null) {
            synchronized (ParkingLotMemory.class) {
                if (instance == null) {
                    instance = new ParkingLotMemory(levels, slotCounts);
                }
            }
        }
        return instance;
    }

    private ParkingLotMemory(Integer levels, List<Integer> slotCounts) {

        this.levels = levels;
        levelMemoryMap = new ConcurrentHashMap<>();
        for (int i = 1; i <= levels; i++) {
            levelMemoryMap.put(i, new ParkingLotLevelWiseMemory(i, slotCounts.get(i - 1)));
        }
    }

    public Integer park(Integer level, Vehicle vehicle) {
        return levelMemoryMap.get(level).park(vehicle);
    }

    public boolean unPark(Integer level, Integer slotNumbers) {
        return levelMemoryMap.get(level).unPark(slotNumbers);
    }

    public List<String> getStatus(Integer level) {
        return levelMemoryMap.get(level).getLevelStatus();
    }

    public List<String> getRegistrationNumbersForColor(Integer level, String color) {
        return levelMemoryMap.get(level).getRegistrationNumbersForColor(color);
    }

    public List<Integer> getSlotNumbersForColor(Integer level, String color) {
        return levelMemoryMap.get(level).getSlotNumbersForColor(color);
    }

    public Integer getSlotNumberForRegistrationNumber(Integer level, String registrationNumber) {
        return levelMemoryMap.get(level).getSlotNumberForRegistrationNumber(registrationNumber);
    }
}
