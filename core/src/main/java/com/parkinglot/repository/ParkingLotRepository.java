package com.parkinglot.repository;

import com.parkinglot.model.ParkingLot;

public interface ParkingLotRepository extends AbstractRepository<ParkingLot> {
    ParkingLot create(ParkingLot parkingLot);
}
