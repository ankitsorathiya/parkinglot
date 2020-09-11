package com.parkinglot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParkVehicle {
    private Integer id;
    private ParkingLot parkingLot;
    private Vehicle vehicle;
    private Date checkInTime;
    private Date checkoutTime;
    private Double durationParked;
    private Double charges;

    @Override
    public String toString() {
        return "ParkVehicle{" +
                "id=" + id +
                ", parkingLot=" + parkingLot.getId() +
                ", vehicle=" + vehicle.getVehicleNumber() +
                ", checkInTime=" + checkInTime +
                ", checkoutTime=" + checkoutTime +
                ", durationParked=" + durationParked +
                ", charges=" + charges +
                '}';
    }
}
