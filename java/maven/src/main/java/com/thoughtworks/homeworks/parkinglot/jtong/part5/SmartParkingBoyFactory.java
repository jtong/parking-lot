package com.thoughtworks.homeworks.parkinglot.jtong.part5;


public class SmartParkingBoyFactory extends ParkingBoyFactory {
    @Override
    public ParkingBoy makeParkingBoy(ParkingLot... parkingLots) {
        return new SmartParkingBoy(parkingLots);
    }
}
