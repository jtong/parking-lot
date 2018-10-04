package com.thoughtworks.homeworks.parkinglot.jtong.part6;


import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ParkingManager {

    private List<ReportingParkable> parkables;

    public ParkingManager(ReportingParkable... parkables) {
        this.parkables = Arrays.asList(parkables);
    }


    public Ticket park(Vehicle vehicle) throws NoEnoughRoomsException {
        ReportingParkable currentParkable = null;
        try {
            currentParkable = parkables.stream()
                    .filter((Parkable parkable) -> !parkable.isFull())
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoEnoughRoomsException(e);
        }
        return currentParkable.park(vehicle);
    }

    public boolean isFull() {
        return parkables.stream().allMatch((ReportingParkable parkable) -> parkable.isFull());
    }

    public Vehicle getVehicle(Ticket parkingTicket) {
        ReportingParkable currentParkable = null;
        try {
            currentParkable = parkables.stream()
                    .filter((Parkable parkingLot) -> parkingLot.containVehicle(parkingTicket))
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
        return currentParkable.getVehicle(parkingTicket);
    }


    public ParkingReport calculateParkingReport() {
        List<ParkingReport> parkingReports = this.parkables.stream().map((ReportingParkable reporter) -> reporter.calculateParkingReport()).collect(Collectors.toList());
        return new ParkingReport("M",parkingReports);
    }
}
