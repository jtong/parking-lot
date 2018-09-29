package com.thoughtworks.homeworks.parkinglot.jtong.part4;


public class ParkingBoyFactory {
    public ParkingBoy makeParkingBoy(ParkingLot... parkingLots) {
        return new ParkingBoy(parkingLots);
    }
}
