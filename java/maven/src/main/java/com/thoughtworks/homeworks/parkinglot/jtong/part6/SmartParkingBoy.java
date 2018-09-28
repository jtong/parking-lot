package com.thoughtworks.homeworks.parkinglot.jtong.part6;

import java.util.Comparator;
import java.util.NoSuchElementException;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(ParkingLot... parkingLots) {
        super(parkingLots);
    }

    @Override
    public String park(Vehicle vehicle) throws NoEnoughRoomsException {
        ParkingLot currentParkingLot = null;
        try {
            currentParkingLot = parkingLots.stream()
                    .filter((ParkingLot parkingLot) -> !parkingLot.isFull())
                    .sorted(Comparator.comparing(ParkingLot::getLeft).reversed())
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoEnoughRoomsException(e);
        }
        return currentParkingLot.park(vehicle);

    }
}
