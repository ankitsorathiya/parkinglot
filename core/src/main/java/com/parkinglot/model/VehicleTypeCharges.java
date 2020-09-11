package com.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehicleTypeCharges {
    private Integer id;
    private VehicleType vehicleType;
    private ParkingLot parkingLot;
    private double hoursRangeStart;
    private double hoursRangeEnd;
    private double charges;
}
