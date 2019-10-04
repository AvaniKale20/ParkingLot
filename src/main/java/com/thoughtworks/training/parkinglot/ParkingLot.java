package com.thoughtworks.training.parkinglot;

import com.thoughtworks.training.parkinglot.exceptions.ParkedException;
import com.thoughtworks.training.parkinglot.exceptions.AlreadyFullParkingLotException;
import com.thoughtworks.training.parkinglot.exceptions.SimilarObjectException;

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

    public void park(Object nextVehicle) throws ParkedException, AlreadyFullParkingLotException {
        if (isSpaceAvailable()) {
            if (isAlreadyParked(nextVehicle)) {
                throw new ParkedException("vehicle parked");
            }
            vehicle.add(nextVehicle);
        } else {
            throw new AlreadyFullParkingLotException("parking lot is already full");
        }
    }


    public Object unPark(Object Vehicle) throws SimilarObjectException {
        if (vehicle.contains(Vehicle)) {
            return vehicle.remove(vehicle.indexOf(Vehicle));
        }
        throw new SimilarObjectException("vehicle not available in parking lot");
    }

    private boolean isAlreadyParked(Object nextVehicle) {
        return vehicle.contains(nextVehicle);
    }

    private boolean isSpaceAvailable() {
        return vehicle.size() < capacity;
    }


}


