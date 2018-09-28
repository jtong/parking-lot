package com.thoughtworks.homeworks.parkinglot.jtong.part6;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SmartParkingBoyTest extends ParkingBoyTest{
    @Before
    public void before(){
        parkingBoyFactory = new SmartParkingBoyFactory();
    }

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

        String ticket = parkingBoy.park(vehicle);
        assertThat(firstParkingLot.isFull(), is(false));
        assertThat(secondParkingLot.getLeft(), is(1));

        parkingBoy.getVehicle(ticket);
        parkingBoy.park(vehicle);

        assertThat(firstParkingLot.isFull(), is(false));
        assertThat(secondParkingLot.getLeft(), is(1));
    }


}
