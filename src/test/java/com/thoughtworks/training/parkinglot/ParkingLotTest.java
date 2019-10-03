package com.thoughtworks.training.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void givenParkingLot_WhenPark_ThenShouldBePark() {
        ParkingLot parkingLot = new ParkingLot(2);
        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLot_WhenPark_ThenShouldNotPark() {
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLot.park(new Object());

        assertFalse(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLot_WhenParkTwoSame_ThenShouldNotPark() {
        ParkingLot parkingLot = new ParkingLot(2);
        Object object1 = new Object();
        parkingLot.park(object1);

        assertThrows(IllegalArgumentException.class, () -> parkingLot.park(object1), "similar object can not allowed");
    }

}
