package com.thoughtworks.training.parkinglot;

import com.thoughtworks.training.parkinglot.exceptions.AlreadyParkedException;
import com.thoughtworks.training.parkinglot.exceptions.ObjectNotParkedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class DummyOwner implements Subscriber {

    public int notifyParkingLotFull = 0;
    public int notifyParkingLotAvailable = 0;

    @Override
    public void notifyParkingLotIsAvailable() {
        notifyParkingLotAvailable++;
    }

    @Override
    public void notifyParkingLotIsFull() {
        notifyParkingLotFull++;
    }
}

class DummySecurity implements Subscriber {

    public int notifyParkingLotFull = 0;
    public int notifyParkingLotAvailable = 0;

    @Override
    public void notifyParkingLotIsFull() {
        notifyParkingLotFull++;
    }

    @Override
    public void notifyParkingLotIsAvailable() {
        notifyParkingLotAvailable++;
    }
}


public class ParkingLotTest {
    // TODO - we don't want to make real owner in your test.

    @Test
    void givenParkingLot_WhenPark_ThenShouldBePark() {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLot_WhenParkTwoDifferentObject_ThenShouldPark() throws Exception {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object objectOne = new Object();
        Object objectTwo = new Object();
        parkingLot.park(objectOne);

        assertDoesNotThrow(() -> parkingLot.park(objectTwo));
    }

    @Test
    void givenParkingLot_WhenParkOneSameObject_ThenShouldNotPark() throws Exception {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);
        Object objectOne = new Object();
        parkingLot.park(objectOne);

        assertThrows(AlreadyParkedException.class, () -> parkingLot.park(objectOne));
    }

    @Test
    void givenParkingLot_WhenUnParkingAParkedVehicle_ThenShouldReturnThatVehicle() throws Exception {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
    }

    @Test
    void givenParkingLot_WhenUnParkingATwoParkedVehicle_ThenShouldReturnThatVehicle() throws Exception {
        DummyOwner owner = new DummyOwner();
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
        DummyOwner owner = new DummyOwner();
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
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        Assertions.assertEquals(1, owner.notifyParkingLotFull);

    }

    @Test
    void givenFullParkingLot_WhenInformOwner_ThenShouldOwnerGetNotifyOne() throws Exception {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        Assertions.assertEquals(1, owner.notifyParkingLotFull);

    }

    @Test
    void givenFullParkingLot_WhenUnParkOneVehicle_ThenNotifyToOwner() throws Exception {
        DummyOwner owner = new DummyOwner();
        ParkingLot parkingLot = new ParkingLot(2, owner);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();

        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        assertEquals(1, owner.notifyParkingLotFull);

        parkingLot.unPark(vehicleTwo);
        assertEquals(1, owner.notifyParkingLotAvailable);

    }

    @Test
    void givenParkingLot_WhenIsFull__ThenInformTheSecurityGuard() throws Exception {
        DummySecurity security = new DummySecurity();
        ParkingLot parkingLot = new ParkingLot(2, security);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, security.notifyParkingLotFull);

    }

    @Test
    void givenFullParkingLot_WhenUnParkOneVehicle__ThenInformTheSecurityGuard() throws Exception {
        DummySecurity security = new DummySecurity();
        ParkingLot parkingLot = new ParkingLot(2, security);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        assertEquals(1, security.notifyParkingLotFull);

        parkingLot.unPark(vehicleOne);
        assertEquals(1, security.notifyParkingLotAvailable);
    }

    @Test
    void givenParkingLot_WhenIsFull__ThenInformTheOwnerAndSecurityGuard() throws Exception {
        DummyOwner owner = new DummyOwner();
        DummySecurity security = new DummySecurity();

        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList(owner, security));

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.notifyParkingLotFull);
        assertEquals(1, security.notifyParkingLotFull);

    }

}