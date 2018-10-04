package com.thoughtworks.homeworks.parkinglot.jtong.part6;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class ParkingBoy implements ReportingParkable {
    protected List<ParkingLot> parkingLots;

    public ParkingBoy(ParkingLot... parkingLots) {
        this.parkingLots = Arrays.asList(parkingLots);
    }

    @Override
    public Ticket park(Vehicle vehicle) throws NoEnoughRoomsException {

        ParkingLot currentParkingLot = null;
        try {
            currentParkingLot = parkingLots.stream()
                    .filter((ParkingLot parkingLot) -> !parkingLot.isFull())
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            throw new NoEnoughRoomsException(e);
        }
        return currentParkingLot.park(vehicle);
    }

    @Override
    public boolean isFull() {
        return parkingLots.stream().allMatch((ParkingLot parkingLot) -> parkingLot.isFull());
    }

    @Override
    public Vehicle getVehicle(Ticket parkingTicket) {
        ParkingLot currentParkingLot = null;
        try {
            currentParkingLot = parkingLots.stream()
                    .filter((ParkingLot parkingLot) -> parkingLot.containVehicle(parkingTicket))
                    .findFirst().get();
        } catch (NoSuchElementException e) {
            return null;
        }
        return currentParkingLot.getVehicle(parkingTicket);
    }

    @Override
    public boolean containVehicle(Ticket parkingTicket) {
        return parkingLots.stream().anyMatch((ParkingLot parkingLot) -> parkingLot.containVehicle(parkingTicket));
    }

    @Override
    public ParkingReport calculateParkingReport() {
        List<ParkingReport> parkingReports = this.parkingLots.stream().map((ReportingParkable reporter) -> reporter.calculateParkingReport()).collect(Collectors.toList());

        return new ParkingReport("B", parkingReports);
    }
}
