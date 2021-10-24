package com.vivek.airplane.model;

public class Seat {
    private Integer index;
    private SeatType seatType;
    private Boolean occupied;
    private Passenger passenger;

    public Seat(Integer index, SeatType seatType, Boolean occupied, Passenger passenger) {
        this.index = index;
        this.seatType = seatType;
        this.occupied = occupied;
        this.passenger = passenger;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "index=" + index +
                ", seatType=" + seatType +
                ", occupied=" + occupied +
                ", passenger=" + passenger +
                '}';
    }
}
