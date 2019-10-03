package com.thoughtworks.training.parkinglot;

public class ParkingLot {
    private int capacity;
    private int availableSpace;
    Object lastVehicle = new Object(); // Initialized with a dummy value

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.availableSpace = capacity;
    }

    public boolean park(Object nextVehicle) {


        if (availableSpace >= capacity) {
            availableSpace--;
            lastVehicle = nextVehicle;
            return true;
        }
        if (nextVehicle.equals(lastVehicle)) {
            throw new IllegalArgumentException("similar object can not allowed");
        }
        return false;
    }
}

