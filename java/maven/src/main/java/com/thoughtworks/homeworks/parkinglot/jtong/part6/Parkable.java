package com.thoughtworks.homeworks.parkinglot.jtong.part6;

public interface Parkable {
    Ticket park(Vehicle vehicle) throws NoEnoughRoomsException;

    boolean isFull();

    Vehicle getVehicle(Ticket parkingTicket);

    boolean containVehicle(Ticket parkingTicket);
}
