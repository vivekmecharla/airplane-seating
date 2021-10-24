package com.vivek.airplane.model;

import java.util.ArrayList;
import java.util.List;

public class SeatGroup {
    private Integer index;
    private Integer numberOfColumns;
    private Integer numberOfRows;
    private List<SeatRow> seatRows;


    public SeatGroup(Integer index, int numberOfColumns, int numberOfRows, List<SeatRow> seatRows) {
        this.index = index;
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.seatRows = seatRows;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public Integer getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(Integer numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public Integer getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(Integer numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public List<SeatRow> getRows() {
        if(null == seatRows) {
            seatRows = new ArrayList<>();
        }
        return seatRows;
    }

    @Override
    public String toString() {
        return "SeatGroup{" +
                "index=" + index +
                ", numberOfColumns=" + numberOfColumns +
                ", numberOfRows=" + numberOfRows +
                ", seatRows=" + seatRows +
                '}';
    }
}
