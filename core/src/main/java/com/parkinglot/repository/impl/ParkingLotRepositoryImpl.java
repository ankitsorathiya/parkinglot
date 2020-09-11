package com.parkinglot.repository.impl;

import com.parkinglot.model.ParkingLot;
import com.parkinglot.repository.ParkingLotRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class ParkingLotRepositoryImpl implements ParkingLotRepository {
    private final Collection<ParkingLot> parkingLots = new ArrayList<>();

    @Override
    public Collection<ParkingLot> findAll() {
        return parkingLots;
    }

    @Override
    public ParkingLot findById(Integer id) {
        Optional<ParkingLot> optionalParkingLot = parkingLots.stream().filter(parkingLot -> parkingLot.getId().equals(id)).findFirst();
        if (optionalParkingLot.isPresent()) {
            return optionalParkingLot.get();
        }
        return null;
    }

    @Override
    public Collection<ParkingLot> createAll(Collection<ParkingLot> newParkingLots) {
        int id = parkingLots.size() + 1;// defining parking lots
        for (ParkingLot parkingLot : newParkingLots) {
            parkingLot.setId(id);
            id++;
        }
        parkingLots.addAll(newParkingLots);
        return newParkingLots;
    }

    @Override
    public ParkingLot create(ParkingLot parkingLot) {
        int id = parkingLots.size() + 1;
        parkingLot.setId(id);
        parkingLots.add(parkingLot);
        return parkingLot;
    }
}
