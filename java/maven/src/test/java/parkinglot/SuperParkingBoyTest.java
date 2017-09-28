package parkinglot;

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
    public void should_parking_vehicle_to_the_lot_whose_empty_room_size_rate_is_largest(){
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
}
