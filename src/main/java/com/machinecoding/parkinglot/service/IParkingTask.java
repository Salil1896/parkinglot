package com.machinecoding.parkinglot.service;

import com.machinecoding.parkinglot.exception.ParkingLotException;

public interface IParkingTask {
    Object execute(String command) throws ParkingLotException;
}
