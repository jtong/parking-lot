package parkinglot;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class SuperParkingBoy extends ParkingBoy {
    public SuperParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public String park(Vehicle vehicle) throws NoEnoughRoomsException {
        ParkingLot currentParkingLot = null;
        try {
            currentParkingLot = parkingLots.stream()
                    .filter((Parkable parkingLot) -> !parkingLot.isFull())
                    .sorted(Comparator.comparing(ParkingLot::getEmptyRate).reversed())
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoEnoughRoomsException(e);
        }
        return currentParkingLot.park(vehicle);

    }
}
