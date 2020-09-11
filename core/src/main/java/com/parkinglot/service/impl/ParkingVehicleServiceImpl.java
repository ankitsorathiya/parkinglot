package com.parkinglot.service.impl;

import com.parkinglot.model.ParkVehicle;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.ParkingLotCapacity;
import com.parkinglot.model.Vehicle;
import com.parkinglot.model.VehicleTypeCharges;
import com.parkinglot.repository.ParkVehicleRepository;
import com.parkinglot.repository.ParkingLotCapacityRepository;
import com.parkinglot.repository.ParkingLotRepository;
import com.parkinglot.service.ParkingVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
public class ParkingVehicleServiceImpl implements ParkingVehicleService {
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private ParkingLotCapacityRepository parkingLotCapacityRepository;
    @Autowired
    private ParkVehicleRepository parkVehicleRepository;

    @Override
    public ParkVehicle parkVehicle(Vehicle vehicle, ParkingLot parkingLot) throws Exception {
        ParkVehicle existingParkedVehicle = parkVehicleRepository.findByVehicleAndParkingLot(vehicle, parkingLot);
        if (Objects.nonNull(existingParkedVehicle)) {
            if (Objects.isNull(existingParkedVehicle.getCheckoutTime())) {
                throw new Exception("You already have checkedin in this parking lot");
            }
        }

        Optional<ParkingLotCapacity> optionalParkingLotCapacity = parkingLot.getParkingLotCapacities().stream().filter(parkingLotCapacity -> parkingLotCapacity.getVehicleType().equals(vehicle.getVehicleType())).findFirst();
        if (!optionalParkingLotCapacity.isPresent()) {
            //user define exception
            throw new Exception("This parking lot does not have ability to park this type of vehicle");
        }
        ParkingLotCapacity parkingLotCapacity = optionalParkingLotCapacity.get();
        if (parkingLotCapacity.getCurrentlyOccupied() >= parkingLotCapacity.getMaxVehicleAllowed()) {
            throw new Exception("Parking lot is full for this type of vehicle");
        }

        parkingLotCapacity.setCurrentlyOccupied(parkingLotCapacity.getCurrentlyOccupied() + 1);// add parked vehicle
        //store parked vehicle
        ParkVehicle parkVehicle = ParkVehicle.builder().vehicle(vehicle)
                .parkingLot(parkingLot)
                .vehicle(vehicle)
                .checkInTime(new Date())
                .build();
        return parkVehicleRepository.create(parkVehicle);
    }

    @Override
    public ParkVehicle checkoutVehicle(Vehicle vehicle, ParkingLot parkingLot) throws Exception {
        ParkVehicle parkVehicle = parkVehicleRepository.findByVehicleAndParkingLot(vehicle, parkingLot);
        if (Objects.isNull(parkVehicle)) {
            throw new Exception("Your vehicle is not parked here");
        }
        if (Objects.nonNull(parkVehicle.getCheckoutTime())) {
            throw new Exception("Your already have checkout from this parking lot");
        }
        Date checkedOutTime = new Date();
        parkVehicle.setCheckoutTime(checkedOutTime);
        long diffInMilliSec = checkedOutTime.getTime() - parkVehicle.getCheckInTime().getTime();
        double hoursParked = (diffInMilliSec / (1000 * 60 * 60)) % 24;

        //move it to respective repo/service
        Optional<ParkingLotCapacity> parkingLotCapacityOptional = parkingLot.getParkingLotCapacities().stream().filter(parkingLotCapacity -> parkingLotCapacity.getVehicleType().getId().equals(vehicle.getVehicleType().getId())).findFirst();
        ParkingLotCapacity parkingLotCapacity = parkingLotCapacityOptional.get();
        //move it to respective repo/service
        Optional<VehicleTypeCharges> optionalVehicleTypeCharges = parkingLotCapacity.getVehicleTypeCharges().stream()
                .filter(vehicleTypeCharges -> vehicleTypeCharges.getVehicleType().getId().equals(vehicle.getVehicleType().getId()))
                .filter(vehicleTypeCharges -> vehicleTypeCharges.getHoursRangeStart() <= hoursParked && vehicleTypeCharges.getHoursRangeEnd() >= hoursParked)
                .findFirst();
        //add default charge 40, or take the max charge, currentlty taking assumption default
        double charges = 40;
        if (optionalVehicleTypeCharges.isPresent()) {
            charges = optionalVehicleTypeCharges.get().getCharges();
        }
        parkingLotCapacity.setCurrentlyOccupied(parkingLotCapacity.getCurrentlyOccupied() - 1);//reduce current occupied
        parkVehicle.setDurationParked(hoursParked);
        parkVehicle.setCharges(charges);
        return parkVehicle;
    }

    @Override
    public Collection<ParkVehicle> findVehicleHistory(Vehicle vehicle) {
        Collection<ParkVehicle> parkVehicles = parkVehicleRepository.findByVehicle(vehicle);
        return parkVehicles;
    }
}
