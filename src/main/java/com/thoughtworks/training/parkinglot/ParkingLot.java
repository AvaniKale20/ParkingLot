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

    public boolean park(Object nextVehicle) throws ParkingLotException {
        if (isFull()) {
            if (isAlreadyParked(nextVehicle))
                throw new ParkingLotException("vehicle parked");
            vehicle.add(nextVehicle);
            return true;
        }
         throw new ParkingLotException("parking lot is already full");
    }

    public boolean unPark(Object Vehicle) {
        if (!vehicle.isEmpty())
            vehicle.contains(Vehicle);
        return vehicle.remove(Vehicle);

    }

    private boolean isAlreadyParked(Object nextVehicle) {
        return vehicle.contains(nextVehicle);
    }

    private boolean isFull() {
        return vehicle.size() <= capacity;
    }


}


