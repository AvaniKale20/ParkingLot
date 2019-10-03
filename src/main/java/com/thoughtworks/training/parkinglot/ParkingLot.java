package com.thoughtworks.training.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;
    private int availableSpace;
    List<Object> vehicle = new ArrayList<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.availableSpace = capacity;
    }

    public boolean park(Object nextVehicle) throws ParkingLotException {
        if (availableSpace >= capacity) {
            availableSpace--;
            vehicle.add(nextVehicle);
            return true;
        }

        if (vehicle.contains(nextVehicle)) {
            throw new ParkingLotException("similar object can not allowed");
        }
        if(vehicle.size()<=capacity){
            throw new ParkingLotException("capacity full");
        }

        return false;

    }
}

