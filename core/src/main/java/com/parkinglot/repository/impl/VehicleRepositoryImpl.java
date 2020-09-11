package com.parkinglot.repository.impl;

import com.parkinglot.model.Vehicle;
import com.parkinglot.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class VehicleRepositoryImpl implements VehicleRepository {
    private final Collection<Vehicle> vehicles = new ArrayList<>();

    @Override
    public Collection<Vehicle> findAll() {
        return vehicles;
    }

    @Override
    public Vehicle findById(Integer id) {
        Optional<Vehicle> optionalVehicle = vehicles.stream().filter(vehicle -> vehicle.getId().equals(id)).findFirst();
        if (optionalVehicle.isPresent()) {
            return optionalVehicle.get();
        }
        return null;
    }

    @Override
    public Collection<Vehicle> createAll(Collection<Vehicle> values) {
        int id = vehicles.size() + 1;// defining parking lots
        for (Vehicle value : values) {
            value.setId(id);
            id++;
        }
        vehicles.addAll(values);
        return values;
    }
}
