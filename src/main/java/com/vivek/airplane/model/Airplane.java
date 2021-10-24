package com.vivek.airplane.model;

import java.util.ArrayList;
import java.util.List;

public class Airplane {
    private List<SeatGroup> seatGroups;

    public List<SeatGroup> getSeatGroups() {
        if (null == seatGroups) {
            seatGroups = new ArrayList<>();
        }
        return seatGroups;
    }

    @Override
    public String toString() {
        return "Airplane{" +
                "seatGroups=" + seatGroups +
                '}';
    }
}
