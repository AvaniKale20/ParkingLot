package com.thoughtworks.training.parkinglot;

import com.thoughtworks.training.parkinglot.exceptions.AlreadyParkedException;
import com.thoughtworks.training.parkinglot.exceptions.ObjectNotParkedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

class DummyPerson implements Subscriber {

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

public class ParkingLotTest {
    // TODO - we don't want to make real owner in your test.

    @Test
    void givenParkingLot_WhenPark_ThenShouldBePark() {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        list.add(owner);

        ParkingLot parkingLot = new ParkingLot(2, list);
        assertDoesNotThrow(() -> parkingLot.park(new Object()));
    }

    @Test
    void givenParkingLot_WhenParkTwoDifferentObject_ThenShouldPark() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        list.add(owner);

        ParkingLot parkingLot = new ParkingLot(2, list);

        Object objectOne = new Object();
        Object objectTwo = new Object();
        parkingLot.park(objectOne);

        assertDoesNotThrow(() -> parkingLot.park(objectTwo));
    }

    @Test
    void givenParkingLot_WhenParkOneSameObject_ThenShouldNotPark() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        list.add(owner);

        ParkingLot parkingLot = new ParkingLot(2, list);
        Object objectOne = new Object();
        parkingLot.park(objectOne);

        assertThrows(AlreadyParkedException.class, () -> parkingLot.park(objectOne));
    }

    @Test
    void givenParkingLot_WhenUnParkingAParkedVehicle_ThenShouldReturnThatVehicle() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        list.add(owner);
        ParkingLot parkingLot = new ParkingLot(2, list);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
    }

    @Test
    void givenParkingLot_WhenUnParkingATwoParkedVehicle_ThenShouldReturnThatVehicle() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        list.add(owner);
        ParkingLot parkingLot = new ParkingLot(2, list);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(vehicleOne, parkingLot.unPark(vehicleOne));
        assertEquals(vehicleTwo, parkingLot.unPark(vehicleTwo));

    }

    @Test
    void givenAddTwoVehicle_WhenUnParkOneSimilarVehicleFromParkingLot_ThenShouldThrowException() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        list.add(owner);
        ParkingLot parkingLot = new ParkingLot(2, list);

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
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        list.add(owner);
        ParkingLot parkingLot = new ParkingLot(2, list);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        Assertions.assertEquals(1, owner.notifyParkingLotFull);

    }

    @Test
    void givenFullParkingLot_WhenInformOwner_ThenShouldOwnerGetNotifyOne() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        list.add(owner);
        ParkingLot parkingLot = new ParkingLot(2, list);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        Assertions.assertEquals(1, owner.notifyParkingLotFull);

    }

    @Test
    void givenFullParkingLot_WhenUnParkOneVehicle_ThenNotifyToOwner() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        list.add(owner);
        ParkingLot parkingLot = new ParkingLot(2, list);

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
        List<Subscriber> list = new ArrayList<>();
        DummySecurity security = new DummySecurity();
        list.add(security);
        ParkingLot parkingLot = new ParkingLot(2, list);

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, security.notifyParkingLotFull);

    }

    @Test
    void givenFullParkingLot_WhenUnParkOneVehicle__ThenInformTheSecurityGuard() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummySecurity security = new DummySecurity();
        list.add(security);
        ParkingLot parkingLot = new ParkingLot(2, list);

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

    @Test
    void givenFullParkingLot_WhenUnParkVehicle_ThenInformTheOwnerAndSecurityGuard() throws Exception {
        DummyOwner owner = new DummyOwner();
        DummySecurity security = new DummySecurity();

        ParkingLot parkingLot = new ParkingLot(2, Arrays.asList(owner, security));

        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.notifyParkingLotFull);
        assertEquals(1, security.notifyParkingLotFull);

        parkingLot.unPark(vehicleOne);
        assertEquals(1, owner.notifyParkingLotAvailable);
        assertEquals(1, security.notifyParkingLotAvailable);
    }

    //For Register person
    @Test
    void givenParkingLot_WhenPersonRegister_ThenNotifyOwnerAndPerson() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        list.add(owner);

        ParkingLot parkingLot = new ParkingLot(2, list);

        //1st resister
        DummyPerson person = new DummyPerson();
        parkingLot.registor(person);

        //then park vehicle
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.notifyParkingLotFull);
        assertEquals(1, person.notifyParkingLotFull);


    }

    @Test
    void givenParkingLot_WhenPersonRegister_ThenNotifyToAllSubscriber() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        DummySecurity security = new DummySecurity();
        list.add(owner);
        list.add(security);

        ParkingLot parkingLot = new ParkingLot(2, list);

        //1st resister
        DummyPerson person = new DummyPerson();
        parkingLot.registor(person);

        //then park vehicle
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);

        assertEquals(1, owner.notifyParkingLotFull);
        assertEquals(1, security.notifyParkingLotFull);
        assertEquals(1, person.notifyParkingLotFull);
    }

    @Test
    void givenFullParkingLot_WhenUnParkedVehicle_ThenNotifyToAllSubscriber() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        DummySecurity security = new DummySecurity();
        list.add(owner);
        list.add(security);

        ParkingLot parkingLot = new ParkingLot(2, list);

        //1st resister
        DummyPerson person = new DummyPerson();
        parkingLot.registor(person);

        //then park vehicle
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        parkingLot.unPark(vehicleOne);

        assertEquals(1, owner.notifyParkingLotFull);
        assertEquals(1, security.notifyParkingLotFull);
        assertEquals(1, person.notifyParkingLotFull);


    }

    @Test
    void givenParkingLot_WhenUnRegisterAnyPerson_ThenNotifyToOwner() throws Exception {
        List<Subscriber> list = new ArrayList<>();
        DummyOwner owner = new DummyOwner();
        list.add(owner);

        ParkingLot parkingLot = new ParkingLot(2, list);

        //1st un resister
        DummyPerson person = new DummyPerson();
        parkingLot.unRegistor(person);

        //then park vehicle
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        parkingLot.unPark(vehicleOne);

        assertEquals(1, owner.notifyParkingLotFull);

    }

    @Test
    void givenParkingLot_WhenUnRegisterAnyPerson_ThenDoNotInformToPerson() throws Exception {
        List<Subscriber> list = new ArrayList<>();

        ParkingLot parkingLot = new ParkingLot(2, list);

        //1st un resister
        DummyPerson person = new DummyPerson();
        parkingLot.unRegistor(person);

        //then park vehicle
        Object vehicleOne = new Object();
        Object vehicleTwo = new Object();
        parkingLot.park(vehicleOne);
        parkingLot.park(vehicleTwo);
        parkingLot.unPark(vehicleOne);

        assertNotEquals(1, person.notifyParkingLotFull);

    }
}