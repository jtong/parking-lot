package com.thoughtworks.homeworks.parkinglot.jtong.part5;

public interface Parkable {
    String park(Vehicle vehicle) throws NoEnoughRoomsException;

    boolean isFull();

    Vehicle getVehicle(String parkingTicket);

    boolean containVehicle(String parkingTicket);

    ParkingReport calculateParkingReport();
}
