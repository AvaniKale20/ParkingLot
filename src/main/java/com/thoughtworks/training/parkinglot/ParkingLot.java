package com.thoughtworks.training.parkinglot;

import com.thoughtworks.training.parkinglot.exceptions.AlreadyParkedException;
import com.thoughtworks.training.parkinglot.exceptions.ParkingLotFullException;
import com.thoughtworks.training.parkinglot.exceptions.ObjectNotParkedException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;
    private Owner owner;
    private String message = "ParkingLot is full";

    public List<Object> vehicle = new ArrayList<>();

    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        this.owner = owner;
    }

    public void park(Object nextVehicle) throws AlreadyParkedException, ParkingLotFullException {
        if (isSpaceAvailable()) {
            if (isAlreadyParked(nextVehicle)) {
                throw new AlreadyParkedException("vehicle parked");
            }
            vehicle.add(nextVehicle);
        } else {
            throw new ParkingLotFullException("parking lot is already full");
        }

        if (vehicle.size() == capacity) {
            owner.notifyParkingLotIsFull();
        }

    }


    public Object unPark(Object Vehicle) throws ObjectNotParkedException {
        if (vehicle.contains(Vehicle)) {
            return vehicle.remove(vehicle.indexOf(Vehicle));
        }
        throw new ObjectNotParkedException("vehicle not available in parking lot");
    }

    private boolean isAlreadyParked(Object nextVehicle) {
        return vehicle.contains(nextVehicle);
    }

    private boolean isSpaceAvailable() {
        return vehicle.size() < capacity;
    }


}


