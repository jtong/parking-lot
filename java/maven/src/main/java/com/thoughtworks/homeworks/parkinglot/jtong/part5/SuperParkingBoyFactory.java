package com.thoughtworks.homeworks.parkinglot.jtong.part5;


public class SuperParkingBoyFactory extends ParkingBoyFactory {
    @Override
    public ParkingBoy makeParkingBoy(ParkingLot... parkingLots) {
        return new SuperParkingBoy(parkingLots);
    }
}
