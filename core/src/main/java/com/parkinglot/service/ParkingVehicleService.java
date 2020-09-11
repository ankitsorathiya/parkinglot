package com.parkinglot.service;

import com.parkinglot.model.ParkVehicle;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Vehicle;

import java.util.Collection;

public interface ParkingVehicleService {
    ParkVehicle parkVehicle(Vehicle vehicle, ParkingLot parkingLot) throws Exception;

    ParkVehicle checkoutVehicle(Vehicle vehicle, ParkingLot parkingLot) throws Exception;

    Collection<ParkVehicle> findVehicleHistory(Vehicle vehicle);
}
