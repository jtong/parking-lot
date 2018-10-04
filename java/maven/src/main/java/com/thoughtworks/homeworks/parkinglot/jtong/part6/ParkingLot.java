package com.thoughtworks.homeworks.parkinglot.jtong.part6;

import java.util.*;


public class ParkingLot implements Parkable, ReportingParkable {
    private int size;
    private Map<Ticket, Vehicle> rooms;


    public ParkingLot(int size) {
        this.size = size;
        rooms = new HashMap<Ticket, Vehicle>();
    }


    public Ticket park(Vehicle vehicle) throws NoEnoughRoomsException {
        if (rooms.size() >= size) {
            throw new NoEnoughRoomsException();
        }
        Ticket parkingTicket = new Ticket(UUID.randomUUID().toString());

        this.rooms.put(parkingTicket,vehicle);
        return parkingTicket;
    }

    public boolean isFull() {
        return size <= rooms.size();
    }

    public Vehicle getVehicle(Ticket parkingTicket) {
        return this.rooms.remove(parkingTicket);
    }

    //TODO: 可不可以用getVehicle替代？
    public boolean containVehicle(Ticket parkingTicket) {
        return this.rooms.containsKey(parkingTicket);
    }


    public int getLeft() {
        return this.size - this.rooms.size();
    }

    //TODO: 到底应该写一个getEmptyRate，还是暴露一个size，让SuperParkingBoy自己算EmptyRate就好了呢？
    public double getEmptyRate() {
        return this.getLeft() / (this.size * 1.0);
    }

    @Override
    public ParkingReport calculateParkingReport() {
        ParkingReport result = new ParkingReport("P", this.size -this.getLeft(), this.size);
        return result;
    }
}
