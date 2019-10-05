package com.thoughtworks.training.parkinglot;

/*
    class - if you know all the behavior concretely / exactly.
    abstract class - if you know only some behavior concretely / exactly, but don't know the rest (for rest, only contract is known). ( 5 methods - 3 methods. 2 method implementation unknown)
    interface - only contract is known.
 */

public interface Owner {

    void notifyParkingLotIsFull();

    void notifyParkingLotIsAvailable();

}


