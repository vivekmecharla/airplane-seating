package com.vivek.airplane.model;

public class Passenger {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private Integer id;

    public Passenger(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                '}';
    }
}
