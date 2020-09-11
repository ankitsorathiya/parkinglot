package com.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {
    private Integer id;
    private VehicleType vehicleType;
    private String vehicleNumber;
    private String vehicleOwnerName;
    private String vehicleOwnerContactNo;
}
