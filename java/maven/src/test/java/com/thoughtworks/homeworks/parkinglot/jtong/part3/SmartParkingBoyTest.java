package com.thoughtworks.homeworks.parkinglot.jtong.part3;


import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;


public class SmartParkingBoyTest{


    @Test
    public void should_parking_vehicle_to_the_lot_whose_empty_room_size_is_largest(){
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        Vehicle vehicle = new Vehicle();

        SmartParkingBoy parkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);

        parkingBoy.park(vehicle);

        assertThat(firstParkingLot.isFull(), is(false));
        assertThat(secondParkingLot.getLeft(), is(1));
    }


    @Test
    public void should_parking_vehicle_to_any_lot_when_given_parking_lot_has_same_empty_room(){
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        List<ParkingLot> allParkingLot = Arrays.asList(firstParkingLot, secondParkingLot);
        Vehicle vehicle = new Vehicle();

        SmartParkingBoy parkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);

        parkingBoy.park(vehicle);

        assertThat(allParkingLot.stream().anyMatch(ParkingLot::isFull), is(true));
        assertThat(allParkingLot.stream().allMatch(ParkingLot::isFull), is(false));
    }


    @Test
    public void should_parking_vehicle_to_the_lot_whose_empty_room_size_has_been_largest_again_after_take_vehicle_with_ticket(){
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(2);
        Vehicle vehicle = new Vehicle();

        SmartParkingBoy parkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);

        Ticket ticket = parkingBoy.park(vehicle);
        assertThat(firstParkingLot.isFull(), is(false));
        assertThat(secondParkingLot.getLeft(), is(1));

        parkingBoy.getVehicle(ticket);
        parkingBoy.park(vehicle);

        assertThat(firstParkingLot.isFull(), is(false));
        assertThat(secondParkingLot.getLeft(), is(1));
    }


    // Parking Boy's Test


    @Test
    public void should_vehicle_can_be_parking_when_there_is_enough_rooms_in_any_parking_lot(){
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        ParkingBoy parkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);

        try {
            parkingBoy.park(firstVehicle);
            parkingBoy.park(secondVehicle);
        } catch (NoEnoughRoomsException e) {
            fail();
        }
    }

    @Test
    public void should_vehicle_can_not_be_parking_when_there_is_no_enough_rooms_in_any_parking_lot(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new SmartParkingBoy(parkingLot);
        Vehicle vehicle = new Vehicle();

        try {
            parkingBoy.park(vehicle);
            parkingBoy.park(vehicle);
            fail();
        } catch (NoEnoughRoomsException e) {

        }
    }

    @Test
    public void should_know_parking_lot_is_not_full_given_any_parking_lot_has_enough_room() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Vehicle firstVehicle = new Vehicle();

        ParkingBoy parkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        parkingBoy.park(firstVehicle);
        assertThat(parkingBoy.isFull(), is(false));
    }

    @Test
    public void should_know_parking_lot_is_full_given_all_parking_lots_has_no_enough_room() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        ParkingBoy parkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        parkingBoy.park(firstVehicle);
        parkingBoy.park(secondVehicle);

        assertThat(parkingBoy.isFull(), is(true));
    }

    @Test
    public void should_get_parked_vehicle_with_ticket_after_parking_in_any_parking_lot(){
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);
        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        parkingBoy.park(firstVehicle);
        Ticket parkingTicket = parkingBoy.park(secondVehicle);

        Vehicle vehicleFromParkingLot = parkingBoy.getVehicle(parkingTicket);
        assertThat(vehicleFromParkingLot, not(firstVehicle));
        assertThat(vehicleFromParkingLot, is(secondVehicle));
    }

    @Test
    public void should_get_null_with_ticket_which_is_not_exist() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);

        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        parkingBoy.park(firstVehicle);
        parkingBoy.park(secondVehicle);
        Ticket parkingTicket = new Ticket("123");

        assertThat(parkingBoy.getVehicle(parkingTicket), is(nullValue()));
    }

    @Test
    public void should_one_ticket_can_only_get_once(){
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);

        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        Ticket parkingTicket = parkingBoy.park(firstVehicle);
        parkingBoy.park(secondVehicle);

        assertThat(parkingBoy.getVehicle(parkingTicket), is(firstVehicle));
        assertThat(parkingBoy.getVehicle(parkingTicket), is(nullValue()));
    }

    @Test
    public void should_vehicle_can_be_parked_when_parking_lot_which_was_full_is_gotten_a_vehicle() {
        ParkingLot firstParkingLot = new ParkingLot(1);
        ParkingLot secondParkingLot = new ParkingLot(1);
        ParkingBoy parkingBoy = new SmartParkingBoy(firstParkingLot, secondParkingLot);

        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();



        try {
            Ticket parkingTicket = parkingBoy.park(firstVehicle);
            parkingBoy.park(secondVehicle);
            parkingBoy.getVehicle(parkingTicket);
            parkingBoy.park(firstVehicle);
        } catch (NoEnoughRoomsException e) {
            fail();
        }
    }
}
