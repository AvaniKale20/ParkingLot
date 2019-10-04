package com.thoughtworks.training.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void givenParkingLot_WhenPark_ThenShouldBePark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLot_WhenPark_ThenShouldNotPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
//        parkingLot.park(new Object());

        assertTrue(parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLot_WhenParkTwoSame_ThenShouldNotPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object object1 = new Object();
        parkingLot.park(object1);

        assertThrows(ParkingLotException.class, () -> parkingLot.park(object1), "similar object can not allowed");
    }

    @Test
    void givenParkingLot_WhenUnParkVehicle_ThenShouldBeUnPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object vehicle1 = parkingLot.park(new Object());
        Object vehicle2 = parkingLot.park(new Object());

        assertFalse(parkingLot.unPark(vehicle1));

    }

    @Test
    void givenParkingLot_WhenUnParkTwoVehicle_ThenShouldUnPark() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object vehicle1 = parkingLot.park(new Object());
        Object vehicle2 = parkingLot.park(new Object());

        assertFalse(parkingLot.unPark(vehicle1));
        assertFalse(parkingLot.unPark(vehicle2));


    }
}
