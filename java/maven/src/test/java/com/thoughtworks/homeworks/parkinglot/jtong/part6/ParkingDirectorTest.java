package com.thoughtworks.homeworks.parkinglot.jtong.part6;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class ParkingDirectorTest {
    @Test
    public void should_print_all_parkable_parking_report_when_there_is_no_vehicle_parking_there(){
        ParkingManager parkingManager = new ParkingManager(new ParkingLot(1));
        ParkingDirector parkingDirector = new ParkingDirector(parkingManager);

        assertThat(parkingDirector.renderReport(), is("M 0 1\n  P 0 1"));

    }

    @Test
    public void should_print_all_parkable_parking_report_when_there_are_two_parking_lots_for_parking_manager(){
        ParkingManager parkingManager = new ParkingManager(new ParkingLot(1), new ParkingLot(1));
        ParkingDirector parkingDirector = new ParkingDirector(parkingManager);

        assertThat(parkingDirector.renderReport(), is("M 0 2\n  P 0 1\n  P 0 1"));

    }

    @Test
    public void should_print_all_parkable_parking_report_when_there_are_one_parking_lot_and_one_parking_boy_with_one_parking_lot(){
        ParkingManager parkingManager = new ParkingManager(new ParkingLot(1), new ParkingBoy(new ParkingLot(1)));
        ParkingDirector parkingDirector = new ParkingDirector(parkingManager);

        assertThat(parkingDirector.renderReport(), is("M 0 2\n  P 0 1\n  B 0 1\n    P 0 1"));

    }


    @Test
    public void should_print_all_parkable_parking_report_when_there_are_multi_parking_lot_and_multi_parking_boys_with_multi_parking_lot(){
        ParkingLot parkingLot = new ParkingLot(10);
        Vehicle vehicle = new Vehicle();
        parkingLot.park(vehicle);
        parkingLot.park(vehicle);


        ParkingBoy firstParkingBoy = new ParkingBoy(new ParkingLot(5));
        firstParkingBoy.park(vehicle);
        firstParkingBoy.park(vehicle);

        ParkingBoy secondParkingBoy = new SuperParkingBoy(new ParkingLot(3), new ParkingLot(2));
        Ticket ticket = secondParkingBoy.park(vehicle);
        secondParkingBoy.park(vehicle);
        secondParkingBoy.getVehicle(ticket);

        ParkingManager parkingManager = new ParkingManager(parkingLot, firstParkingBoy,secondParkingBoy);
        ParkingDirector parkingDirector = new ParkingDirector(parkingManager);

        assertThat(parkingDirector.renderReport(), is("M 5 20\n  P 2 10\n  B 2 5\n    P 2 5\n  B 1 5\n    P 0 3\n    P 1 2"));
    }
}
