package parkinglot;

import java.util.*;


public class ParkingLot implements Parkable{
    private int size;
    private Map<String, Vehicle> rooms;


    public ParkingLot(int size) {
        this.size = size;
        rooms = new HashMap<String, Vehicle>();
    }


    public String park(Vehicle vehicle) throws NoEnoughRoomsException{
        if (rooms.size() >= size) {
            throw new NoEnoughRoomsException();
        }
        String parkingTiket = UUID.randomUUID().toString();

        this.rooms.put(parkingTiket,vehicle);
        return parkingTiket;
    }

    public boolean isFull() {
        return size <= rooms.size();
    }

    public Vehicle getVehicle(String parkingTicket) {
        return this.rooms.remove(parkingTicket);
    }

    public boolean containVehicle(String parkingTicket) {
        return this.rooms.containsKey(parkingTicket);
    }

    public int getLeft() {
        return this.size - this.rooms.size();
    }

    public double getEmptyRate() {
        return this.getLeft() / this.size;
    }
}
