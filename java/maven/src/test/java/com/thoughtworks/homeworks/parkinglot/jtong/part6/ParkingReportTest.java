package com.thoughtworks.homeworks.parkinglot.jtong.part6;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParkingReportTest {
    @Test
    public void should_print_parking_report_with_parking_lot(){
        ParkingLot parkingLot = new ParkingLot(1);
        ParkingReport parkingReport = parkingLot.calculateParkingReport();

        assertThat(parkingReport.renderText(), is("P 0 1"));

    }
}
