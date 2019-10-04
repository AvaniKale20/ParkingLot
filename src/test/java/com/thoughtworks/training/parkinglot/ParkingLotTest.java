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
        Object objectOne = new Object();
        parkingLot.park(objectOne);

        assertThrows(ParkingLotException.class, () -> parkingLot.park(objectOne), "similar object can not allowed");
    }

    @Test
    void givenParkingLot_WhenUnParkVehicle_ThenShouldReturnOneVehicle() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
    }

    @Test
    void givenUnParkTheVehicle_ThenSholudReturnTwoVehicle() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

    }

    @Test
    void givenAddTwoVehicle_WhenUnParkSimilarVehicleFromParkingLot_ThenShouldThrowException() throws ParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

        assertThrows(ParkingLotException.class, () -> parkingLot.unPark(vehicleTwo), "similar vehicle can not Unpark");


    }

}
