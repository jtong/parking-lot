package com.thoughtworks.homeworks.parkinglot.jtong.part6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingReport {
    private int using;
    private int size;
    private String role;
    private List<ParkingReport> parkingReports;

    public ParkingReport(String role, List<ParkingReport> parkingReports) {
        this.role = role;

        this.parkingReports = parkingReports;
    }

    public ParkingReport(String role, int using, int size) {
        this.role = role;
        this.using = using;
        this.size = size;
        this.parkingReports = new ArrayList<>();
    }

    public String renderText() {
        return renderText("");
    }

    private String renderText(final String prefix) {
        String parkingResult = "";
        if (parkingReports.size() != 0) {
            parkingResult = parkingReports.stream()
                    .map((ParkingReport parkingReport) -> "\n" + parkingReport.renderText(prefix + "  "))
                    .collect(Collectors.joining());
        }
        return prefix + this.role + " " + this.getUsing() + " " + this.getSize() + parkingResult;

    }

    private int getUsing() {
        if (parkingReports.size() == 0) {
            return this.using;
        }
        return parkingReports.stream()
                .map((ParkingReport parkingReport) -> parkingReport.getUsing())
                .reduce(0, (Integer lastResult, Integer next) -> lastResult + next);
    }

    private int getSize() {
        if (parkingReports.size() == 0) {
            return this.size;
        }
        return parkingReports.stream()
                .map((ParkingReport parkingReport) -> parkingReport.getSize())
                .reduce(0, (Integer lastResult, Integer next) -> lastResult + next);
    }
}
