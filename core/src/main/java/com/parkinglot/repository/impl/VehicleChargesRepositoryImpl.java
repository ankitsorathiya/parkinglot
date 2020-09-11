package com.parkinglot.repository.impl;

import com.parkinglot.model.VehicleTypeCharges;
import com.parkinglot.repository.VehicleTypeChargesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class VehicleChargesRepositoryImpl implements VehicleTypeChargesRepository {
    private final Collection<VehicleTypeCharges> vehicleTypeCharges = new ArrayList<>();

    @Override
    public Collection<VehicleTypeCharges> findAll() {
        return vehicleTypeCharges;
    }

    @Override
    public VehicleTypeCharges findById(Integer id) {
        Optional<VehicleTypeCharges> optionalParkingLot = vehicleTypeCharges.stream().filter(vehicleType -> vehicleType.getId().equals(id)).findFirst();
        if (optionalParkingLot.isPresent()) {
            return optionalParkingLot.get();
        }
        return null;
    }

    @Override
    public Collection<VehicleTypeCharges> createAll(Collection<VehicleTypeCharges> newVehicleTypeCharges) {
        int id = newVehicleTypeCharges.size() + 1;// defining parking lots
        for (VehicleTypeCharges vehicleTypeCharges : newVehicleTypeCharges) {
            vehicleTypeCharges.setId(id);
            id++;
        }
        vehicleTypeCharges.addAll(newVehicleTypeCharges);
        return newVehicleTypeCharges;
    }
}
