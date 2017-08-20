package parkinglot;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class ParkingLotTest {
    @Test
    public void should_car_can_be_parked_when_there_is_enough_rooms() {
        ParkingLot parkingLot = new ParkingLot(3);
        Vehicle vehicle = new Vehicle();

        try {
            parkingLot.park(vehicle);
        } catch (NoEnoughRoomsException e) {
            fail();
        }
    }

    @Test
    public void should_car_can_not_be_parked_when_there_is_no_enough_rooms() {
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
        String parkingTicket = parkingLot.park(firstVehicle);
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
        String parkingTicket = "123";

        assertThat(parkingLot.getVehicle(parkingTicket), is(nullValue()));
    }

    @Test
    public void should_car_can_be_parked_when_parking_lot_which_was_full_is_gotten_a_car() {
        ParkingLot parkingLot = new ParkingLot(1);
        Vehicle firstVehicle = new Vehicle();
        Vehicle secondVehicle = new Vehicle();

        try {
            String parkingTicket = parkingLot.park(firstVehicle);
            parkingLot.getVehicle(parkingTicket);
            parkingLot.park(secondVehicle);
        } catch (NoEnoughRoomsException e) {
            fail();
        }
    }
}
