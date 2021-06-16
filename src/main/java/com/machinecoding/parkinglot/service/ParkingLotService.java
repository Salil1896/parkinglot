package com.machinecoding.parkinglot.service;


import com.machinecoding.parkinglot.exception.ParkingLotException;
import com.machinecoding.parkinglot.model.Vehicle;

import java.util.List;

/**
 * @author salilmamodiya
 */
public interface ParkingLotService {

    void create(Integer level, List<Integer> slot) throws ParkingLotException;

    void park(Integer level, Vehicle vehicle) throws ParkingLotException;

    void unPark(Integer level, Integer slotNumber) throws ParkingLotException;

    void getParkingStatus(Integer level) throws ParkingLotException;

    void getRegistrationNumbersForColor(Integer level, String color) throws ParkingLotException;

    void getSlotNumbersForColor(Integer level, String color) throws ParkingLotException;

    void getSlotNumberForRegistrationNumber(Integer level, String registrationNumber) throws ParkingLotException;
}
