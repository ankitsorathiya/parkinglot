package com.parkinglot.repository.impl;

import com.parkinglot.model.ParkVehicle;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.repository.ParkVehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParkVehicleRepositoryImpl implements ParkVehicleRepository {
    private final Collection<ParkVehicle> parkVehicles = new ArrayList<>();

    @Override
    public Collection<ParkVehicle> findAll() {
        return parkVehicles;
    }

    @Override
    public ParkVehicle findById(Integer id) {
        Optional<ParkVehicle> optionalParkingLot = parkVehicles.stream().filter(parkingLot -> parkingLot.getId().equals(id)).findFirst();
        if (optionalParkingLot.isPresent()) {
            return optionalParkingLot.get();
        }
        return null;
    }

    @Override
    public Collection<ParkVehicle> createAll(Collection<ParkVehicle> values) {
        int id = parkVehicles.size() + 1;// defining parking lots
        for (ParkVehicle value : values) {
            value.setId(id);
            id++;
        }
        parkVehicles.addAll(values);
        return values;
    }

    @Override
    public ParkVehicle create(ParkVehicle parkVehicle) {
        int id = parkVehicles.size() + 1;
        parkVehicle.setId(id);
        parkVehicles.add(parkVehicle);
        return parkVehicle;
    }

    @Override
    public ParkVehicle findByVehicleAndParkingLot(Vehicle vehicle, ParkingLot parkingLot) {
        List<ParkVehicle> allParking = parkVehicles.stream().filter(parkVehicle -> parkVehicle.getVehicle().getId().equals(vehicle.getId()) && parkVehicle.getParkingLot().getId().equals(parkingLot.getId())).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(allParking)) {
            return null;
        }
        return allParking.get(allParking.size() - 1);
    }

    @Override
    public Collection<ParkVehicle> findByVehicle(Vehicle vehicle) {
        return parkVehicles.stream().filter(parkVehicle -> parkVehicle.getVehicle().getId().equals(vehicle.getId())).collect(Collectors.toList());
    }
}
