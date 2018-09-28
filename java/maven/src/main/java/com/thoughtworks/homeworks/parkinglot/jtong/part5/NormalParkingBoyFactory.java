package com.thoughtworks.homeworks.parkinglot.jtong.part5;

public class NormalParkingBoyFactory implements ParkingBoyFactory {
    @Override
    public ParkingBoy makeParkingBoy(ParkingLot... parkingLots) {
        return new ParkingBoy(parkingLots);
    }
}
