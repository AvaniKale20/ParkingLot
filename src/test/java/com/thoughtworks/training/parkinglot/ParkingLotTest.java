package com.thoughtworks.training.parkinglot;

import com.thoughtworks.training.parkinglot.exceptions.AlreadyParkedException;
import com.thoughtworks.training.parkinglot.exceptions.ParkingLotFullException;
import com.thoughtworks.training.parkinglot.exceptions.ObjectNotParkedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLotTest {
    Owner owner = new Owner();

    @Test
    void givenParkingLot_WhenPark_ThenShouldBePark() {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLot_WhenParkTwoDifferentObject_ThenShouldPark() throws AlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object objectOne = new Object();
        Object objectTwo = new Object();
        parkingLot.park(objectOne);

        assertDoesNotThrow(() -> parkingLot.park(objectTwo));
    }

    @Test
    void givenParkingLot_WhenParkOneSameObject_ThenShouldNotPark() throws AlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object objectOne = new Object();
        parkingLot.park(objectOne);

        assertThrows(AlreadyParkedException.class, () -> parkingLot.park(objectOne), "similar object can not allowed");
    }

    @Test
    void givenParkingLot_WhenUnParkingAParkedVehicle_ThenShouldReturnThatVehicle() throws ObjectNotParkedException, AlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
    }

    @Test
    void givenParkingLot_WhenUnParkingATwoParkedVehicle_ThenShouldReturnThatVehicle() throws ObjectNotParkedException, AlreadyParkedException, ParkingLotFullException {
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

    }

    @Test
    void givenAddTwoVehicle_WhenUnParkOneSimilarVehicleFromParkingLot_ThenShouldThrowException() throws ObjectNotParkedException, ParkingLotFullException, AlreadyParkedException {
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

        assertThrows(ObjectNotParkedException.class, () -> parkingLot.unPark(vehicleTwo), "Vehicle already un park from parkingLot  ");


    }

    @Test
    void givenParkingLot_WhenIsFull__ThenInformTheOwner() throws AlreadyParkedException, ParkingLotFullException {
        DummyOwner dummyOwner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        Assertions.assertTrue(dummyOwner.wasCalled);


    }

//    @Test
//    void givenFullParkingLot_WhenCalledOneTime_ThenShouldReturnCountOne() throws AlreadyParkedException, ParkingLotFullException {
//        DummyOwner dummyOwner = new DummyOwner();
//        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);
//        Object vehicleOne = new Object();
//        Object vehicleTwo = new Object();
//        parkingLot.park(vehicleOne);
//        parkingLot.park(vehicleTwo);
//        Assertions.assertEquals(1, dummyOwner.noOfTimeNotified);
//
//
//    }

    public class DummyOwner extends Owner {

        private boolean wasCalled = false;
//        int noOfTimeNotified = 0;

        @Override
        public void notifyParkingLotIsFull() {
            wasCalled = true;
//            noOfTimeNotified++;
//            noOfTimeNotified = noOfTimeNotified + 1;

        }


    }
}