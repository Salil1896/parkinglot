package com.machinecoding.parkinglot;


import com.machinecoding.parkinglot.service.IParkingTask;
import com.machinecoding.parkinglot.service.Impl.ParkingLotCommandExecutionService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * @author salilmamodiya
 */
public class Main {

    public static void main(String args[]) {

        BufferedReader bufferReader = null;
        String input;
        IParkingTask task = new ParkingLotCommandExecutionService();

        try {
            switch (args.length) {
                case 0:
                    bufferReader = new BufferedReader(new InputStreamReader(System.in));
                    while (true) {
                        input = bufferReader.readLine();
                        if (input.equals("exit")) break;
                        task.execute(input);
                    }
                    break;
                case 1:
                    bufferReader = new BufferedReader(new FileReader(new File(args[0])));
                    while (true) {
                        input = bufferReader.readLine();
                        if (input == null) break;
                        task.execute(input);
                    }
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
