package com.thoughtworks.training.parkinglot.exceptions;

public class AlreadyParkedException extends Exception {
    public AlreadyParkedException(String s) {
        super(s);
    }
}