package com.parkinglot.repository.impl;

import com.parkinglot.model.VehicleType;
import com.parkinglot.repository.VehicleTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class VehicleTypeRepositoryImpl implements VehicleTypeRepository {
    private final Collection<VehicleType> vehicleTypes = new ArrayList<>();

    @Override
    public Collection<VehicleType> findAll() {
        return vehicleTypes;
    }

    @Override
    public VehicleType findById(Integer id) {
        Optional<VehicleType> optionalParkingLot = vehicleTypes.stream().filter(vehicleType -> vehicleType.getId().equals(id)).findFirst();
        if (optionalParkingLot.isPresent()) {
            return optionalParkingLot.get();
        }
        return null;
    }

    @Override
    public Collection<VehicleType> createAll(Collection<VehicleType> newVehicleTypes) {
        int id = vehicleTypes.size() + 1;// defining parking lots
        for (VehicleType vehicleType : newVehicleTypes) {
            vehicleType.setId(id);
            id++;
        }
        vehicleTypes.addAll(vehicleTypes);
        return newVehicleTypes;
    }
}
