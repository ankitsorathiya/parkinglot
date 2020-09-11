package com.parkinglot.repository.impl;

import com.parkinglot.model.ParkingLotCapacity;
import com.parkinglot.repository.ParkingLotCapacityRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class ParkingLotCapacityRepositoryImpl implements ParkingLotCapacityRepository {
    private final Collection<ParkingLotCapacity> parkingLotCapacities = new ArrayList<>();

    @Override
    public Collection<ParkingLotCapacity> findAll() {
        return parkingLotCapacities;
    }

    @Override
    public ParkingLotCapacity findById(Integer id) {
        Optional<ParkingLotCapacity> optionalParkingLot = parkingLotCapacities.stream().filter(parkingLot -> parkingLot.getId().equals(id)).findFirst();
        if (optionalParkingLot.isPresent()) {
            return optionalParkingLot.get();
        }
        return null;
    }

    @Override
    public Collection<ParkingLotCapacity> createAll(Collection<ParkingLotCapacity> values) {
        int id = parkingLotCapacities.size() + 1;// defining parking lots
        for (ParkingLotCapacity value : values) {
            value.setId(id);
            id++;
        }
        parkingLotCapacities.addAll(values);
        return values;
    }
}
