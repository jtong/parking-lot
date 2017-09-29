package parkinglot;


import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ParkingManager {

    private List<Parkable> parkables;

    public ParkingManager(Parkable... parkables) {
        this.parkables = Arrays.asList(parkables);
    }


    public String park(Vehicle vehicle) throws NoEnoughRoomsException {
        Parkable currentParkable = null;
        try {
            currentParkable = parkables.stream()
                    .filter((Parkable parkable) -> !parkable.isFull())
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoEnoughRoomsException(e);
        }
        return currentParkable.park(vehicle);
    }

    public boolean isFull() {
        return parkables.stream().allMatch((Parkable parkable) -> parkable.isFull());
    }

    public Vehicle getVehicle(String parkingTicket) {
        Parkable currentParkable = null;
        try {
            currentParkable = parkables.stream()
                    .filter((Parkable parkingLot) -> parkingLot.containVehicle(parkingTicket))
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
        return currentParkable.getVehicle(parkingTicket);
    }


}
