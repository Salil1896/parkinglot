package com.machinecoding.parkinglot.service.Impl;


import com.machinecoding.parkinglot.constants.AppConstants;
import com.machinecoding.parkinglot.dao.ParkingLotMemory;
import com.machinecoding.parkinglot.exception.ParkingLotException;
import com.machinecoding.parkinglot.model.Vehicle;
import com.machinecoding.parkinglot.service.ParkingLotService;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author salilmamodiya
 */
public class ParkingLotServiceImpl implements ParkingLotService {

    private ParkingLotMemory parkingLotMemory = null;

    @Override
    public void create(Integer levels, List<Integer> slots) throws ParkingLotException {
        if (parkingLotMemory != null) {
            throw new ParkingLotException("Parking Lot Already created...");
        }

        parkingLotMemory = ParkingLotMemory.getInstance(levels, slots);
        System.out.println("Created a parking lot with " + slots.get(0) + " slots");
    }


    @Override
    public void park(Integer level, Vehicle vehicle) throws ParkingLotException {

        try {
            Integer slotNumber = parkingLotMemory.park(level, vehicle);

            if (slotNumber == AppConstants.NOT_AVAILABLE) {
                System.out.println("Sorry, parking lot is full");
            } else {
                System.out.println("Allocated slot number: " + slotNumber);
            }
        } catch (Exception ex) {
            throw new ParkingLotException("Exception in park...");
        }
    }

    @Override
    public void unPark(Integer level, Integer slotNumber) throws ParkingLotException {

        try {
            if (parkingLotMemory.unPark(level, slotNumber)) {
                System.out.println("Slot number " + slotNumber + " is free");
            } else {
                // slot already empty. printing same
                System.out.println("Slot number " + slotNumber + " is free");
            }
        } catch (Exception ex) {
            throw new ParkingLotException("Exception in unPark...");
        }
    }

    @Override
    public void getParkingStatus(Integer level) throws ParkingLotException {

        try {
            System.out.println("Slot No.    Registration No    Colour");
            List<String> statusList = parkingLotMemory.getStatus(level);

            if (statusList.size() > 0) {
                for (int i = 0; i < statusList.size(); i++) {
                    System.out.println(statusList.get(i));
                }
            }
        } catch (Exception ex) {
            throw new ParkingLotException("Exception in getParkingStatus...");
        }
    }


    @Override
    public void getRegistrationNumbersForColor(Integer level, String color) throws ParkingLotException {
        try {
            List<String> registrationNumbers = parkingLotMemory.getRegistrationNumbersForColor(level, color);
            if (registrationNumbers.size() == 0) {
                System.out.println("Not found");
            } else {
                System.out.println(String.join(", ", registrationNumbers));
            }
        } catch (Exception ex) {
            throw new ParkingLotException("Exception in getRegistrationNumbersForColor...");
        }
    }


    @Override
    public void getSlotNumbersForColor(Integer level, String color) throws ParkingLotException {
        try {
            List<Integer> slotNumbers = parkingLotMemory.getSlotNumbersForColor(level, color);
            if (slotNumbers.size() == 0) {
                System.out.println("Not found");
            } else {
                System.out.println(
                        String.join(", ", slotNumbers.stream().map(x -> x.toString()).collect(Collectors.toList()))
                );
            }
        } catch (Exception ex) {
            throw new ParkingLotException("Exception in getSlotNumbersForColor...");
        }
    }


    @Override
    public void getSlotNumberForRegistrationNumber(Integer level, String registrationNumber) throws ParkingLotException {

        try {
            Integer slotNumber = parkingLotMemory.getSlotNumberForRegistrationNumber(level, registrationNumber);
            if (slotNumber == AppConstants.NOT_AVAILABLE) {
                System.out.println("Not found");
            } else {
                System.out.println(slotNumber);
            }
        } catch (Exception ex) {
            throw new ParkingLotException("Exception in getSlotNumberForRegistrationNumber...");
        }
    }
}
