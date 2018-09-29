package com.thoughtworks.homeworks.parkinglot.jtong.part5;

public class ParkingBoyFactory {
    public ParkingBoy makeParkingBoy(ParkingLot... parkingLots) {
        return new ParkingBoy(parkingLots);
    }
}
