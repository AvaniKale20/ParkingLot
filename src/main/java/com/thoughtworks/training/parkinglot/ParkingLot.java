package com.thoughtworks.training.parkinglot;

import com.thoughtworks.training.parkinglot.exceptions.AlreadyParkedException;
import com.thoughtworks.training.parkinglot.exceptions.ParkingLotFullException;
import com.thoughtworks.training.parkinglot.exceptions.ObjectNotParkedException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;
    private Owner owner;

    public List<Object> vehicle = new ArrayList<>();

    public ParkingLot(int capacity, Owner owner) {
        this.capacity = capacity;
        this.owner = owner;
    }


    public void park(Object nextVehicle) throws AlreadyParkedException, ParkingLotFullException {
        if (isSpaceAvailable()) {
            if (isAlreadyParked(nextVehicle)) {
                throw new AlreadyParkedException();
            }
            vehicle.add(nextVehicle);
            informTheOwnerParkingLotIsFull();
        } else {
            throw new ParkingLotFullException();
        }
    }


    public Object unPark(Object Vehicle) throws ObjectNotParkedException {
        if (isAlreadyParked(Vehicle)) {
            Object storeVehicle = vehicle.remove(vehicle.indexOf(Vehicle));

            if (vehicle.size() == capacity - 1) {
                informToOwnerParkingLotAvailable();
            }
            return storeVehicle;
        }
        throw new ObjectNotParkedException();
    }


    private void informToOwnerParkingLotAvailable() {
        if (vehicle.size() == capacity - 1) {
            owner.notifyParkingLotIsAvailable();
        }
    }

    private void informTheOwnerParkingLotIsFull() {
        if (vehicle.size() == capacity) {
            owner.notifyParkingLotIsFull();
        }
    }

    private boolean isAlreadyParked(Object nextVehicle) {
        return vehicle.contains(nextVehicle);
    }

    private boolean isSpaceAvailable() {
        return vehicle.size() < capacity;
    }


}


