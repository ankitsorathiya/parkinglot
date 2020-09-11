package com.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkingLotCapacity {
    private Integer id;
    private ParkingLot parkingLot;
    private VehicleType vehicleType;
    private long maxVehicleAllowed;
    private long currentlyOccupied;
    private Collection<VehicleTypeCharges> vehicleTypeCharges;
}
