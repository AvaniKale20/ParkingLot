package com.thoughtworks.training.parkinglot;

public class DummySecurity implements Subscriber {

    public int notifyParkingLotFull = 0;
    public int notifyParkingLotAvailable = 0;

    @Override
    public void notifyParkingLotIsFull() {
        notifyParkingLotFull++;
    }

    @Override
    public void notifyParkingLotIsAvailable() {
        notifyParkingLotAvailable++;
    }

}
