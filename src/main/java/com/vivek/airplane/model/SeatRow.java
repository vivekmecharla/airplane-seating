package com.vivek.airplane.model;

import java.util.ArrayList;
import java.util.List;

public class SeatRow {
    private Integer index;
    private List<Seat> seats;

    public SeatRow(Integer index, List<Seat> seats) {
        this.index = index;
        this.seats = seats;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public List<Seat> getSeats() {
        if(null == seats) {
            seats = new ArrayList<>();
        }
        return seats;
    }

    @Override
    public String toString() {
        return "Row{" +
                "index=" + index +
                ", seats=" + seats +
                '}';
    }
}
