package com.thoughtworks.training.parkinglot;

import com.thoughtworks.training.parkinglot.exceptions.AlreadyParkedException;
import com.thoughtworks.training.parkinglot.exceptions.ParkingLotFullException;
import com.thoughtworks.training.parkinglot.exceptions.ObjectNotParkedException;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private int capacity;
    private List<Subscriber> parkingLotSubscriber;

    public List<Object> vehicle = new ArrayList<>();

    public ParkingLot(int capacity, List<Subscriber> parkingLotSubscriber) {
        this.capacity = capacity;
        this.parkingLotSubscriber = parkingLotSubscriber;
    }

//    public ParkingLot(int capacity, Subscriber subscriber) {
//        this.capacity = capacity;
//        this.subscriber = subscriber;
//        this.parkingLotSubscriber = new ArrayList<>();
//    }

    public void park(Object nextVehicle) throws AlreadyParkedException, ParkingLotFullException {
        if (isSpaceAvailable()) {
            if (isAlreadyParked(nextVehicle)) {
                throw new AlreadyParkedException();
            }
            vehicle.add(nextVehicle);

            if (informParkingLotIsFull()) {
                for (Subscriber p1 : parkingLotSubscriber)
                    p1.notifyParkingLotIsFull();
            }
        } else {
            throw new ParkingLotFullException();
        }
    }


    public Object unPark(Object Vehicle) throws ObjectNotParkedException {
        if (isAlreadyParked(Vehicle)) {
            Object storeVehicle = vehicle.remove(vehicle.indexOf(Vehicle));

            if (informSpaceIsAvailable()) {
                for (Subscriber p : parkingLotSubscriber)
                    p.notifyParkingLotIsAvailable();
            }
            return storeVehicle;
        }
        throw new ObjectNotParkedException();
    }


    private boolean informSpaceIsAvailable() {
        return (vehicle.size() == capacity - 1);
    }

    private boolean informParkingLotIsFull() {
        return (vehicle.size() == capacity);
    }

    private boolean isAlreadyParked(Object nextVehicle) {
        return vehicle.contains(nextVehicle);
    }

    private boolean isSpaceAvailable() {
        return vehicle.size() < capacity;
    }

    public void registor(Subscriber newPerson) {
        parkingLotSubscriber.add(newPerson);
    }
}


