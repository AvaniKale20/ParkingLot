package com.thoughtworks.training.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;
    private int availableSpace;
    public List<Object> vehicle = new ArrayList<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.availableSpace = capacity;
    }

    public void park(Object nextVehicle) throws ParkingLotException {
        if (isSpaceAvailable()) {
            if (isAlreadyParked(nextVehicle)) {
                throw new ParkingLotException("vehicle parked");
            }
            vehicle.add(nextVehicle);
        } else {
            throw new ParkingLotException("parking lot is already full");
        }
    }

    public Object unPark(Object Vehicle) throws ParkingLotException {
        if (vehicle.contains(Vehicle)) {
            return vehicle.remove(vehicle.indexOf(Vehicle));
        }
        throw new ParkingLotException("vehicle not available in parking lot");
    }

    private boolean isAlreadyParked(Object nextVehicle) {
        return vehicle.contains(nextVehicle);
    }

    private boolean isSpaceAvailable() {
        return vehicle.size() < capacity;
    }


}


