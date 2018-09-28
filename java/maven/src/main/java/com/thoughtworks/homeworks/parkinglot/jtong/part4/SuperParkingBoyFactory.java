package com.thoughtworks.homeworks.parkinglot.jtong.part4;


public class SuperParkingBoyFactory implements ParkingBoyFactory {
    @Override
    public ParkingBoy makeParkingBoy(ParkingLot... parkingLots) {
        return new SuperParkingBoy(parkingLots);
    }
}
