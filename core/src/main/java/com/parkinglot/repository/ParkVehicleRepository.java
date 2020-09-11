package com.parkinglot.repository;

import com.parkinglot.model.ParkVehicle;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Vehicle;

import java.util.Collection;

public interface ParkVehicleRepository extends AbstractRepository<ParkVehicle> {
    ParkVehicle create(ParkVehicle parkVehicle);

    ParkVehicle findByVehicleAndParkingLot(Vehicle vehicle, ParkingLot parkingLot);

    Collection<ParkVehicle> findByVehicle(Vehicle vehicle);

}
