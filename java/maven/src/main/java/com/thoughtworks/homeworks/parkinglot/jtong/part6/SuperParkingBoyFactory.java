package com.thoughtworks.homeworks.parkinglot.jtong.part6;

public class SuperParkingBoyFactory implements ParkingBoyFactory {
    @Override
    public ParkingBoy makeParkingBoy(ParkingLot... parkingLots) {
        return new SuperParkingBoy(parkingLots);
    }
}
