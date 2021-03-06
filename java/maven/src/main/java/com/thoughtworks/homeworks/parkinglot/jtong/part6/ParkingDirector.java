package com.thoughtworks.homeworks.parkinglot.jtong.part6;

public class ParkingDirector {

    private ParkingManager parkingManager;

    public ParkingDirector(ParkingManager parkingManagers) {

        this.parkingManager = parkingManagers;
    }

    public String renderReport() {
        ParkingReport result =  parkingManager.calculateParkingReport();
        return result.renderText();
    }
}
