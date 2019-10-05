package com.thoughtworks.training.parkinglot;

public class DummyPerson implements Subscriber {

    public int notifyParkingLotFull = 0;
    public int notifyParkingLotAvailable = 0;

    @Override
    public void notifyParkingLotIsAvailable() {
        notifyParkingLotAvailable++;
    }

    @Override
    public void notifyParkingLotIsFull() {
        notifyParkingLotFull++;
    }

}