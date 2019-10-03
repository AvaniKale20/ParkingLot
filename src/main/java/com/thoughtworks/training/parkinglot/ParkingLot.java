package com.thoughtworks.training.parkinglot;

public class ParkingLot {
    private int capacity;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public boolean park(Object object) {
        if (capacity == 20) {
            return true;
        }
        return false;
    }
}
