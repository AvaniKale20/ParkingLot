package com.thoughtworks.training.parkinglot.exceptions;

public class AlreadyFullParkingLotException extends Exception {
    public AlreadyFullParkingLotException(String s) {
        super(s);
    }
}
