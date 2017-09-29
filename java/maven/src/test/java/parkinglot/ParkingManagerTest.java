package parkinglot;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ParkingManagerTest {


    @Test
    public void should_vehicle_can_be_parking_when_there_is_enough_rooms_in_any_packable_object(){
        Parkable parkingLot = new ParkingLot(1);
        Parkable parkingBoy = new ParkingBoy(new ParkingLot(1));
        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        ParkingManager parkingManager = new ParkingManager(parkingLot, parkingBoy);

        try {
            parkingManager.park(firstVehicle);
            parkingManager.park(secondVehicle);
        } catch (NoEnoughRoomsException e) {
            fail();
        }
    }

    @Test
    public void should_tell_full_when_there_is_no_any_rooms_in_any_packable_object(){
        Parkable parkingLot = new ParkingLot(1);
        Parkable parkingBoy = new ParkingBoy(new ParkingLot(1));
        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        ParkingManager parkingManager = new ParkingManager(parkingLot, parkingBoy);
        parkingManager.park(firstVehicle);
        parkingManager.park(secondVehicle);

        assertThat(parkingManager.isFull(), is(true));
    }

    @Test
    public void should_not_tell_full_when_there_is_any_rooms_in_any_packable_object(){
        Parkable parkingLot = new ParkingLot(1);
        Parkable parkingBoy = new ParkingBoy(new ParkingLot(1));
        Vehicle vehicle = new Vehicle();

        ParkingManager parkingManager = new ParkingManager(parkingLot, parkingBoy);

        try {
            parkingManager.park(vehicle);
        } catch (NoEnoughRoomsException e) {
            fail();
        }

        assertThat(parkingManager.isFull(), is(false));
    }


    @Test
    public void should_get_parked_vehicle_with_ticket_after_parking_in_any_parking_lot(){
        Parkable parkingLot = new ParkingLot(1);
        Parkable parkingBoy = new ParkingBoy(new ParkingLot(1));
        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        ParkingManager parkingManager = new ParkingManager(parkingLot, parkingBoy);

        String firstTicket = parkingManager.park(firstVehicle);
        parkingManager.park(secondVehicle);


        Vehicle vehicleFromParkableObject = parkingManager.getVehicle(firstTicket);
        assertThat(vehicleFromParkableObject, is(firstVehicle));
        assertThat(vehicleFromParkableObject, not(secondVehicle));
    }


    @Test
    public void should_vehicle_can_not_be_parking_when_there_is_no_enough_rooms_in_any_parkable_object(){
        Parkable parkingLot = new ParkingLot(1);
        Parkable parkingBoy = new ParkingBoy(new ParkingLot(1));
        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();
        Vehicle thirdVehicle = new Vehicle();

        ParkingManager parkingManager = new ParkingManager(parkingLot, parkingBoy);

        try {
            parkingManager.park(firstVehicle);
            parkingManager.park(secondVehicle);
            parkingManager.park(thirdVehicle);

            fail();
        } catch (NoEnoughRoomsException e) {
        }
    }


    @Test
    public void should_get_null_with_ticket_which_is_not_exist() {
        Parkable parkingLot = new ParkingLot(1);
        Parkable parkingBoy = new ParkingBoy(new ParkingLot(1));
        ParkingManager parkingManager = new ParkingManager(parkingLot, parkingBoy);

        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        parkingManager.park(firstVehicle);
        parkingManager.park(secondVehicle);
        String parkingTicket = "123";

        assertThat(parkingManager.getVehicle(parkingTicket), is(nullValue()));
    }


    @Test
    public void should_one_ticket_can_only_get_once(){
        Parkable parkingLot = new ParkingLot(1);
        Parkable parkingBoy = new ParkingBoy(new ParkingLot(1));
        ParkingManager parkingManager = new ParkingManager(parkingLot, parkingBoy);

        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        String parkingTicket =parkingManager.park(firstVehicle);
        parkingManager.park(secondVehicle);


        assertThat(parkingManager.getVehicle(parkingTicket), is(firstVehicle));
        assertThat(parkingManager.getVehicle(parkingTicket), is(nullValue()));
    }

    @Test
    public void should_vehicle_can_be_parked_when_parking_lot_which_was_full_is_gotten_a_vehicle() {
        Parkable parkingLot = new ParkingLot(1);
        Parkable parkingBoy = new ParkingBoy(new ParkingLot(1));
        ParkingManager parkingManager = new ParkingManager(parkingLot, parkingBoy);

        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        try {
            String parkingTicket = parkingManager.park(firstVehicle);
            parkingManager.park(secondVehicle);
            parkingManager.getVehicle(parkingTicket);
            parkingManager.park(firstVehicle);
        } catch (NoEnoughRoomsException e) {
            fail();
        }
    }
    
    @Test
    public void should_all_parkable_object_can_be_managed_to_park(){
        Parkable parkingLot = new ParkingLot(1);
        Parkable parkingBoy = new ParkingBoy(new ParkingLot(1));
        Parkable smartParkingBoy = new SmartParkingBoy(new ParkingLot(1));
        Parkable superParkingBoy = new SuperParkingBoy(new ParkingLot(1));
        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();
        Vehicle thirdVehicle = new Vehicle();
        Vehicle fourthVehicle = new Vehicle();

        ParkingManager parkingManager = new ParkingManager(parkingLot, parkingBoy, smartParkingBoy, superParkingBoy);

        try {
            parkingManager.park(firstVehicle);
            parkingManager.park(secondVehicle);
            parkingManager.park(thirdVehicle);
            parkingManager.park(fourthVehicle);

        } catch (NoEnoughRoomsException e) {
            fail();
        }

        assertThat(parkingLot.isFull(), is(true));
        assertThat(parkingBoy.isFull(), is(true));
        assertThat(smartParkingBoy.isFull(), is(true));
        assertThat(superParkingBoy.isFull(), is(true));
    }

    @Test
    public void should_all_parkable_object_can_be_managed_to_get_vehicle() {
        Parkable parkingLot = new ParkingLot(1);
        Parkable parkingBoy = new ParkingBoy(new ParkingLot(1));
        Parkable smartParkingBoy = new SmartParkingBoy(new ParkingLot(1));
        Parkable superParkingBoy = new SuperParkingBoy(new ParkingLot(1));
        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();
        Vehicle thirdVehicle = new Vehicle();
        Vehicle fourthVehicle = new Vehicle();

        ParkingManager parkingManager = new ParkingManager(parkingLot, parkingBoy, smartParkingBoy, superParkingBoy);

        String firstTicket = parkingManager.park(firstVehicle);
        String secondTicket = parkingManager.park(secondVehicle);
        String thirdTicket = parkingManager.park(thirdVehicle);
        String fourthTicket = parkingManager.park(fourthVehicle);


        assertThat(parkingManager.getVehicle(firstTicket), is(firstVehicle));
        assertThat(parkingManager.getVehicle(secondTicket), is(secondVehicle));
        assertThat(parkingManager.getVehicle(thirdTicket), is(thirdVehicle));
        assertThat(parkingManager.getVehicle(fourthTicket), is(fourthVehicle));
    }


}
