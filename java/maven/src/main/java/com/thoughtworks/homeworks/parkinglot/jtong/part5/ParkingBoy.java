package com.thoughtworks.homeworks.parkinglot.jtong.part5;


import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class ParkingBoy implements Parkable {
    protected List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    @Override
    public Ticket park(Vehicle vehicle) throws NoEnoughRoomsException {

        ParkingLot currentParkingLot = null;
        try {
            currentParkingLot = parkingLots.stream()
                    .filter((ParkingLot parkingLot) -> !parkingLot.isFull())
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoEnoughRoomsException(e);
        }
        return currentParkingLot.park(vehicle);
    }

    @Override
    public boolean isFull() {
        return parkingLots.stream().allMatch((ParkingLot parkingLot) -> parkingLot.isFull());
    }

    @Override
    public Vehicle getVehicle(Ticket parkingTicket) {
        ParkingLot currentParkingLot = null;
        try {
            currentParkingLot = parkingLots.stream()
                    .filter((ParkingLot parkingLot) -> parkingLot.containVehicle(parkingTicket))
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
        return currentParkingLot.getVehicle(parkingTicket);
    }

    @Override
    public boolean containVehicle(Ticket parkingTicket) {
        return parkingLots.stream().anyMatch((ParkingLot parkingLot) -> parkingLot.containVehicle(parkingTicket));
    }
}
