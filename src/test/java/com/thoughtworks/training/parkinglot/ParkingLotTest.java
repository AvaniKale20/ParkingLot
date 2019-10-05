package com.thoughtworks.training.parkinglot;

import com.thoughtworks.training.parkinglot.exceptions.AlreadyParkedException;
import com.thoughtworks.training.parkinglot.exceptions.ParkingLotFullException;
import com.thoughtworks.training.parkinglot.exceptions.ObjectNotParkedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DummyOwner extends Owner {

//    public boolean wasCalled = false; // TODO - maybe wasCalled is not needed.
    int notifyParkingLotFull = 0;
    int notifyParkingLotAvailable = 0;

    @Override
    public void notifyParkingLotIsAvailable() {
        notifyParkingLotAvailable++;
    }

    @Override
    public void notifyParkingLotIsFull() {
//        wasCalled = true;
        notifyParkingLotFull++;
    }


}

public class ParkingLotTest {
    Owner owner = new Owner(); // TODO - we don't want to make real owner in your test.
    DummyOwner dummyOwner = new DummyOwner();

    @Test
    void givenParkingLot_WhenPark_ThenShouldBePark() {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    // TODO - change to base class Exception
    @Test
    void givenParkingLot_WhenParkTwoDifferentObject_ThenShouldPark() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object objectOne = new Object();
        Object objectTwo = new Object();
        parkingLot.park(objectOne);

        assertDoesNotThrow(() -> parkingLot.park(objectTwo));
    }

    @Test
    void givenParkingLot_WhenParkOneSameObject_ThenShouldNotPark() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object objectOne = new Object();
        parkingLot.park(objectOne);

        assertThrows(AlreadyParkedException.class, () -> parkingLot.park(objectOne));
    }

    @Test
    void givenParkingLot_WhenUnParkingAParkedVehicle_ThenShouldReturnThatVehicle() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
    }

    @Test
    void givenParkingLot_WhenUnParkingATwoParkedVehicle_ThenShouldReturnThatVehicle() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

    }

    @Test
    void givenAddTwoVehicle_WhenUnParkOneSimilarVehicleFromParkingLot_ThenShouldThrowException() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

        assertThrows(ObjectNotParkedException.class, () -> parkingLot.unPark(vehicleTwo));


    }

    @Test
    void givenParkingLot_WhenIsFull__ThenInformTheOwner() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        Assertions.assertEquals(1,dummyOwner.notifyParkingLotFull);

    }

    @Test
    void givenFullParkingLot_WhenInformOwner_ThenShouldOwnerGetNotifyOne() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        Assertions.assertEquals(1, dummyOwner.notifyParkingLotFull);

    }

    @Test
    void givenFullParkingLot_WhenUnParkOneVehicle_ThenNotifyToOwner() throws Exception {
        ParkingLot parkingLot = new ParkingLot(2, dummyOwner);
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        assertEquals(1, dummyOwner.notifyParkingLotFull);

        parkingLot.unPark(vehicleTwo);
        assertEquals(1, dummyOwner.notifyParkingLotAvailable);
    }
}