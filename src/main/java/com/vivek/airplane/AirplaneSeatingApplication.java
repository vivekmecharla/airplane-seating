package com.vivek.airplane;

import com.vivek.airplane.exception.SeatsInsufficientException;
import com.vivek.airplane.model.Airplane;
import com.vivek.airplane.model.PrintOperationType;
import com.vivek.airplane.service.AirplaneService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AirplaneSeatingApplication {

/*
Sample Input
------------
4
3 2
4 3
2 3
3 4

*/

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("Enter number of seating groups:");
            int noOfSeatingGroups = Integer.parseInt(reader.readLine());

            int[][] inputArr = new int[noOfSeatingGroups][2];
            System.out.println("Enter seat group dimensions in separate lines separated by a space (eg. 3 4):");

            for (int i = 0; i < noOfSeatingGroups; i++) {
                String readLine = reader.readLine();
                String[] split = readLine.split(" ");

                inputArr[i][0] = Integer.parseInt(split[0]);
                inputArr[i][1] = Integer.parseInt(split[1]);
            }

            System.out.println("Enter number of Passengers:");
            int passengerCount = Integer.parseInt(reader.readLine());

            //Create Airplane Model based on input Seating Groups
            AirplaneService airplaneService = new AirplaneService();
            Airplane airplaneModel = airplaneService.createAirplaneModel(inputArr);

            //Print the Airplane Model with Seat type (WINDOW/MIDDLE/AISLE)
            System.out.println("Airplane Seating Plan: ");
            airplaneService.performPrintOperation(airplaneModel, PrintOperationType.PRINT_SEATING_ARRANGEMENT);

            //Arrange the passengers in the seats of the airplane as per the business logic
            airplaneService.arrangePassengersInAirplane(airplaneModel, passengerCount);

            System.out.println("Airplane Seating after Boarding: ");
            airplaneService.performPrintOperation(airplaneModel, PrintOperationType.PRINT_PASSENGERS_LIST);
        } catch (IOException | SeatsInsufficientException e) {
            e.printStackTrace();
        }


    }

}
