package parkinglot;

public class SuperParkingBoyFactory implements ParkingBoyFactory {
    @Override
    public ParkingBoy makeParkingBoy(ParkingLot... parkingLots) {
        return new SuperParkingBoy(parkingLots);
    }
}
