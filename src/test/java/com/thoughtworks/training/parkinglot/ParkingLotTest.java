package com.thoughtworks.training.parkinglot;

import com.thoughtworks.training.parkinglot.exceptions.ParkedException;
import com.thoughtworks.training.parkinglot.exceptions.AlreadyFullParkingLotException;
import com.thoughtworks.training.parkinglot.exceptions.SimilarObjectException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    @Test
    void givenParkingLot_WhenPark_ThenShouldBePark() {
        ParkingLot parkingLot = new ParkingLot(2);
        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLot_WhenParkTwoDifferentObject_ThenShouldPark() throws ParkedException, AlreadyFullParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object objectOne = new Object();
        Object objectTwo = new Object();
        parkingLot.park(objectOne);

        assertDoesNotThrow(() -> parkingLot.park(objectTwo));
    }

    @Test
    void givenParkingLot_WhenParkOneSameObject_ThenShouldNotPark() throws ParkedException, AlreadyFullParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object objectOne = new Object();
        parkingLot.park(objectOne);

        assertThrows(ParkedException.class, () -> parkingLot.park(objectOne), "similar object can not allowed");
    }

    @Test
    void givenParkingLot_WhenUnParkingAParkedVehicle_ThenShouldReturnThatVehicle() throws SimilarObjectException, ParkedException, AlreadyFullParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
    }

    @Test
    void givenParkingLot_WhenUnParkingATwoParkedVehicle_ThenShouldReturnThatVehicle() throws SimilarObjectException, ParkedException, AlreadyFullParkingLotException {
        ParkingLot parkingLot = new ParkingLot(2);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

    }

    @Test
    void givenAddTwoVehicle_WhenUnParkOneSimilarVehicleFromParkingLot_ThenShouldThrowException() throws SimilarObjectException, AlreadyFullParkingLotException, ParkedException {
        ParkingLot parkingLot = new ParkingLot(2);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

        assertThrows(SimilarObjectException.class, () -> parkingLot.unPark(vehicleTwo), "Vehicle already un park from parkingLot  ");


    }

}
