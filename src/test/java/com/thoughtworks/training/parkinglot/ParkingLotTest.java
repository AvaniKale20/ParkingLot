package com.thoughtworks.training.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void givenParkingLot_WhenPark_ThenShouldBePark() {
        ParkingLot parkingLot = new ParkingLot(2);
        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLot_WhenPark_ThenShouldNotPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(new Object());

        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLot_WhenParkTwoSame_ThenShouldNotPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object object1 = new Object();
        parkingLot.park(object1);

        assertThrows(ParkingLotException.class, () -> parkingLot.park(object1), "similar object can not allowed");
    }
}
