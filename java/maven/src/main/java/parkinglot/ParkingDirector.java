package parkinglot;

import java.util.Arrays;

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
