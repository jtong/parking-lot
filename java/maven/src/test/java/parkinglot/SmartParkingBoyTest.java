package parkinglot;

import org.junit.Before;


public class SmartParkingBoyTest extends ParkingBoyTest{
    @Before
    public void before(){
        parkingBoyFactory = new SmartParkingBoyFactory();
    }
}
