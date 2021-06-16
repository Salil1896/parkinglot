package com.machinecoding.parkinglot.service.Impl;


import com.machinecoding.parkinglot.constants.CommandConstants;
import com.machinecoding.parkinglot.exception.ParkingLotException;
import com.machinecoding.parkinglot.model.Car;
import com.machinecoding.parkinglot.service.IParkingTask;
import com.machinecoding.parkinglot.service.ParkingLotService;

import java.util.Arrays;

public class ParkingLotCommandExecutionService implements IParkingTask {

    private ParkingLotService parkingLotService;

    public ParkingLotCommandExecutionService() {
        parkingLotService = new ParkingLotServiceImpl();
    }

    @Override
    public String execute(String command) throws ParkingLotException {

        String[] commands = command.split(" ");

        switch (commands[0]) {

            case CommandConstants.CREATE_PARKING_LOT:
                parkingLotService.create(1,
                        Arrays.asList(Integer.parseInt(commands[1])));
                break;
            case CommandConstants.PARK:
                parkingLotService.park(1, new Car(commands[1], commands[2]));
                break;
            case CommandConstants.LEAVE:
                parkingLotService.unPark(1, Integer.parseInt(commands[1]));
                break;
            case CommandConstants.STATUS:
                parkingLotService.getParkingStatus(1);
                break;
            case CommandConstants.REG_NUMS_FOR_COLOR:
                parkingLotService.getRegistrationNumbersForColor(1, commands[1]);
                break;
            case CommandConstants.SLOT_NUMS_FOR_COLOR:
                parkingLotService.getSlotNumbersForColor(1, commands[1]);
                break;
            case CommandConstants.SLOT_NUM_FOR_REG_NUM:
                parkingLotService.getSlotNumberForRegistrationNumber(1, commands[1]);
                break;
            default:
                throw new ParkingLotException("Invalid Command");
                //System.out.println("Invalid Command");

        }
        return "Done";
    }
}
