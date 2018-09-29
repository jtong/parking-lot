package com.thoughtworks.homeworks.parkinglot.jtong.part4;

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
                    .filter((ParkingLot parkingLot) -> !parkingLot.isFull())
//                .sorted((next, pre) ->
//                        pre.calculateEmptyRate() >= next.calculateEmptyRate() ? 1 : -1)
                    .sorted(Comparator.comparing(ParkingLot::getEmptyRate).reversed())
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoEnoughRoomsException(e);
        }
        return currentParkingLot.park(vehicle);

    }
}
