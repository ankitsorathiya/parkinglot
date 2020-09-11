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
public class ParkingLot {
    private Integer id;
    private String name;
    private Double latitude;
    private Double longitude;
    private String address;
    private Collection<ParkingLotCapacity> parkingLotCapacities;
}
