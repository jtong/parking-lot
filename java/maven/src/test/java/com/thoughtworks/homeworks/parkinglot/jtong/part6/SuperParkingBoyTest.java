package com.thoughtworks.homeworks.parkinglot.jtong.part6;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SuperParkingBoyTest extends ParkingBoyTest {
    @Before
    public void before(){
        parkingBoyFactory = new SuperParkingBoyFactory();
    }


    @Test
    public void should_parking_vehicle_to_the_lot_whose_empty_rate_is_largest(){
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(3);
        Vehicle vehicle = new Vehicle();

        SuperParkingBoy parkingBoy = new SuperParkingBoy(firstParkingLot, secondParkingLot);

        parkingBoy.park(vehicle);

        assertThat(firstParkingLot.getLeft(), is(4));

        parkingBoy.park(vehicle);
        assertThat(firstParkingLot.getLeft(), is(4));
        assertThat(secondParkingLot.getLeft(), is(2));

        parkingBoy.park(vehicle);
        assertThat(firstParkingLot.getLeft(), is(3));
        assertThat(secondParkingLot.getLeft(), is(2));
    }

    @Test
    public void should_parking_vehicle_to_any_lot_when_given_parking_lot_has_same_empty_rate(){
        ParkingLot firstParkingLot = new ParkingLot(2);
        ParkingLot secondParkingLot = new ParkingLot(4);
        Vehicle vehicle = new Vehicle();

        firstParkingLot.park(vehicle);
        secondParkingLot.park(vehicle);
        secondParkingLot.park(vehicle);

        SuperParkingBoy parkingBoy = new SuperParkingBoy(firstParkingLot, secondParkingLot);

        parkingBoy.park(vehicle);

        assertThat(secondParkingLot.isFull() || firstParkingLot.getLeft() == 0, is(true));
    }



    @Test
    public void should_parking_vehicle_to_the_lot_whose_empty_rate_has_been_largest_again_after_take_vehicle_with_ticket(){
        ParkingLot firstParkingLot = new ParkingLot(5);
        ParkingLot secondParkingLot = new ParkingLot(3);
        Vehicle vehicle = new Vehicle();

        SuperParkingBoy parkingBoy = new SuperParkingBoy(firstParkingLot, secondParkingLot);

        parkingBoy.park(vehicle);

        assertThat(firstParkingLot.getLeft(), is(4));

        String ticket = parkingBoy.park(vehicle);
        assertThat(firstParkingLot.getLeft(), is(4));
        assertThat(secondParkingLot.getLeft(), is(2));

        parkingBoy.getVehicle(ticket);
        parkingBoy.park(vehicle);
        assertThat(firstParkingLot.getLeft(), is(4));
        assertThat(secondParkingLot.getLeft(), is(2));


    }
}
