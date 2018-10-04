package com.thoughtworks.homeworks.parkinglot.jtong.part6;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ParkingLotTest {
    @Test
    public void should_vehicle_can_be_parked_when_there_is_enough_rooms() {
        ParkingLot parkingLot = new ParkingLot(3);
        Vehicle vehicle = new Vehicle();

        try {
            parkingLot.park(vehicle);
        } catch (NoEnoughRoomsException e) {
            fail();
        }
    }

    @Test
    public void should_vehicle_can_not_be_parked_when_there_is_no_enough_rooms() {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = new Vehicle();

        try {
            parkingLot.park(vehicle);
            parkingLot.park(vehicle);
            fail();
        } catch (NoEnoughRoomsException e) {

        }
    }

    @Test
    public void should_know_parking_lot_is_not_full_given_parking_lot_has_enough_room() {
        ParkingLot parkingLot = new ParkingLot(2);

        assertThat(parkingLot.isFull(), is(false));
    }

    @Test
    public void should_know_parking_lot_is_full_given_parking_lot_has_no_enough_room() {
        ParkingLot parkingLot = new ParkingLot(1);

        parkingLot.park(new Vehicle());

        assertThat(parkingLot.isFull(), is(true));
    }

    @Test
    public void should_get_parked_vehicle_with_ticket_after_parking() {
        ParkingLot parkingLot = new ParkingLot(2);

        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();
        Ticket parkingTicket = parkingLot.park(firstVehicle);
        parkingLot.park(secondVehicle);

        assertThat(parkingLot.getVehicle(parkingTicket), is(firstVehicle));
    }

    @Test
    public void should_get_null_with_ticket_which_is_not_exist() {
        ParkingLot parkingLot = new ParkingLot(2);

        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();
        parkingLot.park(firstVehicle);
        parkingLot.park(secondVehicle);
        Ticket parkingTicket = new Ticket("123");

        assertThat(parkingLot.getVehicle(parkingTicket), is(nullValue()));
    }

    @Test
    public void should_vehicle_can_be_parked_when_parking_lot_which_was_full_is_gotten_a_vehicle() {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        try {
            Ticket parkingTicket = parkingLot.park(firstVehicle);
            parkingLot.getVehicle(parkingTicket);
            parkingLot.park(secondVehicle);
        } catch (NoEnoughRoomsException e) {
            fail();
        }
    }

    @Test
    public void should_tell_true_if_parking_lot_contain_a_vehicle(){
        ParkingLot parkingLot = new ParkingLot(2);

        Vehicle vehicle = new Vehicle();
        Ticket parkingTicket = parkingLot.park(vehicle);

        assertThat(parkingLot.containVehicle(parkingTicket), is(true));
    }

    @Test
    public void should_get_left_as_empty_room_size_of_a_parking_lot(){
        ParkingLot parkingLot = new ParkingLot(2);

        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);

        assertThat(parkingLot.getLeft(), is(1));
    }

    @Test
    public void should_get_left_recovered_after_get_a_vehicle_from_a_parking_lot(){
        ParkingLot parkingLot = new ParkingLot(2);

        Vehicle vehicle = new Vehicle();
        Ticket ticket = parkingLot.park(vehicle);
        parkingLot.getVehicle(ticket);

        assertThat(parkingLot.getLeft(), is(2));
    }

    @Test
    public void should_always_be_zero_after_parking_a_vehicl_to_a_full_parking_lot_and_handled_exception(){
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle vehicle = new Vehicle();

        try {
            parkingLot.park(vehicle);
            parkingLot.park(vehicle);
            fail();
        } catch (NoEnoughRoomsException e) {

        }
        assertThat(parkingLot.getLeft(), is(0));
    }
}
