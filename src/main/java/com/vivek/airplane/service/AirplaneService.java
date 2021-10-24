package com.vivek.airplane.service;

import com.vivek.airplane.exception.SeatsInsufficientException;
import com.vivek.airplane.model.*;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;

public class AirplaneService {

    NumberFormat formatter = new DecimalFormat("000");

    public Airplane createAirplaneModel(int[][] inputArray) {
        Airplane airplane = new Airplane();
        int totalNumberOfSeatGroups = inputArray.length;
        for (int seatGroupIndex = 0; seatGroupIndex < totalNumberOfSeatGroups; seatGroupIndex++) {
            SeatGroup seatGroup = getSeatGroup(inputArray[seatGroupIndex], totalNumberOfSeatGroups, seatGroupIndex);
            airplane.getSeatGroups().add(seatGroup);
        }
        return airplane;
    }

    public void performPrintOperation(Airplane airplane, PrintOperationType printOperationType) {
        int maxNoOfRows = airplane.getSeatGroups().stream().max(Comparator.comparing(SeatGroup::getNumberOfRows)).get().getNumberOfRows();

        for (int rowNumber = 0; rowNumber <= maxNoOfRows; rowNumber++) {
            printRow(airplane, rowNumber, printOperationType);
            System.out.println();
        }
    }

    public Airplane arrangePassengersInAirplane(Airplane airplane, int passengerCount) throws SeatsInsufficientException {
        Queue<Passenger> passengerQueue = new LinkedList<>();
        for (int passengerIndex = 1; passengerIndex <= passengerCount; passengerIndex++) {
            passengerQueue.add(new Passenger(passengerIndex));
        }
        fillSeats(airplane, passengerQueue, SeatType.AIS);
        fillSeats(airplane, passengerQueue, SeatType.WIN);
        fillSeats(airplane, passengerQueue, SeatType.MID);

        if (!passengerQueue.isEmpty())
            throw new SeatsInsufficientException("Seats are not sufficient for the number of passengers provided");
        else
            return airplane;
    }

    private SeatGroup getSeatGroup(int[] seatGroupArray, int totalNumberOfSeatGroups, int seatGroupIndex) {
        int numberOfRows = seatGroupArray[1];
        int numberOfColumns = seatGroupArray[0];
        List<SeatRow> seatRows = new ArrayList<>();
        for (int rowIndex = 0; rowIndex < numberOfRows; rowIndex++) {
            SeatRow seatRow = getRow(totalNumberOfSeatGroups, seatGroupIndex, numberOfColumns, rowIndex);
            seatRows.add(seatRow);
        }
        return new SeatGroup(seatGroupIndex, numberOfColumns, numberOfRows, seatRows);
    }

    private SeatRow getRow(int totalNumberOfSeatGroups, int seatGroupIndex, int numberOfColumns, int rowIndex) {
        List<Seat> seats = new ArrayList<>();
        for (int seatIndex = 0; seatIndex < numberOfColumns; seatIndex++) {
            Seat seat = getSeat(totalNumberOfSeatGroups, seatGroupIndex, numberOfColumns, seatIndex);
            seats.add(seat);
        }
        SeatRow seatRow = new SeatRow(rowIndex, seats);
        return seatRow;
    }

    private Seat getSeat(int numberOfSeatGroups, int seatGroupIndex, int numberOfColumns, int seatIndex) {
        Seat seat = new Seat(seatIndex, getSeatType(numberOfSeatGroups, seatGroupIndex, numberOfColumns, seatIndex), false, null);
        return seat;
    }

    //Core logic to calculate Seat type
    private SeatType getSeatType(int numberOfSeatGroups, int seatGroupIndex, int numberOfColumns, int seatIndex) {
        SeatType seatType;
        if ((seatIndex == 0 && seatGroupIndex == 0)
                || (seatIndex == numberOfColumns - 1 && seatGroupIndex == numberOfSeatGroups - 1)) {
            seatType = SeatType.WIN;
        } else if (seatIndex == 0 || seatIndex == numberOfColumns - 1) {
            seatType = SeatType.AIS;
        } else {
            seatType = SeatType.MID;
        }
        return seatType;
    }

    private void printRow(Airplane airplane, int rowNumber, PrintOperationType printOperationType) {
        for (SeatGroup seatGroup : airplane.getSeatGroups()) {
            for (int columnNumber = 0; columnNumber < seatGroup.getNumberOfColumns(); columnNumber++) {
                if (seatGroup.getRows().size() > rowNumber) {
                    SeatRow seatRow = seatGroup.getRows().get(rowNumber);
                    Seat seat = seatRow.getSeats().get(columnNumber);
                    printSeat(seat, printOperationType);
                } else {
                    System.out.print("    ");
                }
            }
            System.out.print("   ");
        }
    }

    private void printSeat(Seat seat, PrintOperationType printOperationType) {
        switch (printOperationType) {
            case PRINT_SEATING_ARRANGEMENT -> System.out.print(seat.getSeatType() + " ");
            case PRINT_PASSENGERS_LIST -> {
                if (null != seat.getPassenger()) {
                    int passengerId = seat.getPassenger().getId();
                    System.out.print(formatter.format(passengerId) + " ");
                } else {
                    System.out.print("    ");
                }
            }
        }
    }

    private Airplane fillSeats(Airplane airplane, Queue<Passenger> passengerQueue, SeatType seatType) {
        int maxNoOfRows = airplane.getSeatGroups().stream().max(Comparator.comparing(SeatGroup::getNumberOfRows)).get().getNumberOfRows();
        for (int rowNumber = 0; rowNumber < maxNoOfRows; rowNumber++) {
            fillRow(airplane, passengerQueue, rowNumber, seatType);
        }
        return airplane;
    }

    private void fillRow(Airplane airplane, Queue<Passenger> passengerQueue, int rowNumber, SeatType seatType) {

        for (SeatGroup seatGroup : airplane.getSeatGroups()) {
            for (int columnNumber = 0; columnNumber < seatGroup.getNumberOfColumns(); columnNumber++) {
                if (seatGroup.getRows().size() > rowNumber) {
                    if (!passengerQueue.isEmpty()) {
                        SeatRow seatRow = seatGroup.getRows().get(rowNumber);
                        Seat seat = seatRow.getSeats().get(columnNumber);
                        if (seat.getSeatType() == seatType) {
                            seat.setPassenger(passengerQueue.poll());
                            seat.setOccupied(true);
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }
}
