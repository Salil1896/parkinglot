package com.machinecoding.parkinglot;


import com.machinecoding.parkinglot.exception.ParkingLotException;
import com.machinecoding.parkinglot.model.Car;
import com.machinecoding.parkinglot.service.Impl.ParkingLotServiceImpl;
import com.machinecoding.parkinglot.service.ParkingLotService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

public class ParkingLotTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();


    @Before
    public void beforeTest() {
        System.setOut(new PrintStream(outputStream));
    }

    @After
    public void afterTest() {
        System.setOut(null);
    }

    /**
     * Complete Flow, Given Test
     */
    @Test
    public void Test1() throws Exception {

        /**
         *  case1: Create parking Lot Test
         */
        ParkingLotService parkingLotService = new ParkingLotServiceImpl();
        parkingLotService.create(1, Arrays.asList(6));

        /**
         * Parking AllocationTest
         */
        parkingLotService.park(1, new Car("KA-01-HH-1234", "White"));
        parkingLotService.park(1, new Car("KA-01-HH-9999", "White"));
        parkingLotService.park(1, new Car("KA-01-BB-0001", "Black"));
        parkingLotService.park(1, new Car("KA-01-HH-7777", "Red"));
        parkingLotService.park(1, new Car("KA-01-HH-2701", "Blue"));
        parkingLotService.park(1, new Car("KA-01-HH-3141", "Black"));


        /**
         *leave test
         */
        parkingLotService.unPark(1, 4);

        /**
         * Parking status test
         */
        parkingLotService.getParkingStatus(1);

        /**
         * Again assign car to slot 4
         */
        parkingLotService.park(1, new Car("KA-01-P-333", "White"));

        /**
         * Parking lot is full
         */
        parkingLotService.park(1, new Car("DL-12-AA-9999", "White"));


        /**
         *registration_numbers_for_cars_with_colour
         */
        parkingLotService.getRegistrationNumbersForColor(1, "White");

        /**
         *slot_numbers_for_cars_with_colour
         */
        parkingLotService.getSlotNumbersForColor(1, "White");

        /**
         * slot_number_for_registration_number
         */
        parkingLotService.getSlotNumberForRegistrationNumber(1, "KA-01-HH-3141");
        parkingLotService.getSlotNumberForRegistrationNumber(1, "MH-04-AY-1111");

        String output = outputStream.toString();

        Assert.assertTrue(("Created a parking lot with 6 slots\n" +

                "Allocated slot number: 1\n" +
                "Allocated slot number: 2\n" +
                "Allocated slot number: 3\n" +
                "Allocated slot number: 4\n" +
                "Allocated slot number: 5\n" +
                "Allocated slot number: 6\n" +

                "Slot number 4 is free\n" +

                "Slot No.    Registration No    Colour\n" +
                "1           KA-01-HH-1234      White\n" +
                "2           KA-01-HH-9999      White\n" +
                "3           KA-01-BB-0001      Black\n" +
                "5           KA-01-HH-2701      Blue\n" +
                "6           KA-01-HH-3141      Black\n" +

                "Allocated slot number: 4\n" +

                "Sorry, parking lot is full\n" +

                "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333\n" +

                "1, 2, 4\n" +

                "6\n" +

                "Not found\n").equals(output));


    }


    @Test
    public void Test2() {

        try {
            /**
             *  case1: Creating parking lot again will give exception
             */
            ParkingLotService parkingLotService = new ParkingLotServiceImpl();
            parkingLotService.create(1, Arrays.asList(6));
            parkingLotService.create(1, Arrays.asList(5));
        } catch (ParkingLotException ex) {
            Assert.assertTrue(ex.getMessage().equals("Parking Lot Already created..."));
        }
    }


}
