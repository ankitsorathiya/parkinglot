package com.parkinglot;


import com.parkinglot.model.ParkVehicle;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.ParkingLotCapacity;
import com.parkinglot.model.Vehicle;
import com.parkinglot.model.VehicleType;
import com.parkinglot.model.VehicleTypeCharges;
import com.parkinglot.repository.ParkingLotCapacityRepository;
import com.parkinglot.repository.ParkingLotRepository;
import com.parkinglot.repository.VehicleRepository;
import com.parkinglot.repository.VehicleTypeChargesRepository;
import com.parkinglot.repository.VehicleTypeRepository;
import com.parkinglot.service.ParkingVehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@SpringBootApplication
@ComponentScan("com.parkinglot")
@Slf4j
public class Application {
    @Autowired
    private ParkingVehicleService parkingVehicleService;
    @Autowired
    private ParkingLotRepository parkingLotRepository;
    @Autowired
    private ParkingLotCapacityRepository parkingLotCapacityRepository;
    @Autowired
    private VehicleRepository vehicleRepository;
    @Autowired
    private VehicleTypeRepository vehicleTypeRepository;
    @Autowired
    private VehicleTypeChargesRepository vehicleTypeChargesRepository;


    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    void run() throws Exception {
        ParkingLot parkingLot = ParkingLot.builder()
                .name("Alpah 1")
                .address("near hsr")
                .latitude(null)
                .longitude(null)
                .build();
        parkingLot = parkingLotRepository.create(parkingLot);

        //define vehicle and vehicle type
        VehicleType vehicleType = VehicleType.builder().name("SUV").build();
        Collection<VehicleType> vehicleTypes = vehicleTypeRepository.createAll(Arrays.asList(vehicleType));
        VehicleTypeCharges vehicleTypeCharges = VehicleTypeCharges.builder().charges(100)
                .hoursRangeStart(0)
                .hoursRangeEnd(2)
                .charges(20)
                .vehicleType(vehicleTypes.iterator().next())
                .parkingLot(parkingLot).build();

        Collection<VehicleTypeCharges> storedVehicleTypeCharges = vehicleTypeChargesRepository.createAll(Arrays.asList(vehicleTypeCharges));
        ParkingLotCapacity parkingLotCapacity = ParkingLotCapacity.builder()
                .parkingLot(parkingLot)
                .currentlyOccupied(0)
                .maxVehicleAllowed(1)
                .vehicleType(vehicleTypes.iterator().next())
                .vehicleTypeCharges(storedVehicleTypeCharges)
                .build();
        Collection<ParkingLotCapacity> parkingLotCapacities = parkingLotCapacityRepository.createAll(Arrays.asList(parkingLotCapacity));
        parkingLot.setParkingLotCapacities(parkingLotCapacities);

        Vehicle vehicle = Vehicle.builder().vehicleNumber("100")
                .vehicleOwnerContactNo("7447425789")
                .vehicleOwnerName("ankit")
                .vehicleType(vehicleTypes.iterator().next()).build();

        Vehicle vehicle2 = Vehicle.builder().vehicleNumber("101")
                .vehicleOwnerContactNo("7447425780")
                .vehicleOwnerName("ankit1")
                .vehicleType(vehicleTypes.iterator().next()).build();

        List<Vehicle> vehicles = (List<Vehicle>) vehicleRepository.createAll(Arrays.asList(vehicle, vehicle2));

        System.out.println("parking first vehicle");
        ParkVehicle parkVehicle = parkingVehicleService.parkVehicle(vehicles.get(0), parkingLot);
        System.out.println(parkVehicle);

        System.out.println("checkout first vehicle");
        ParkVehicle checkoutVehicle = parkingVehicleService.checkoutVehicle(vehicles.get(0), parkingLot);
        System.out.println("checkout first vehicle" + checkoutVehicle);


        System.out.println("parking second vehicle");
        ParkVehicle parkVehicle1 = parkingVehicleService.parkVehicle(vehicles.get(1), parkingLot);
        System.out.println(parkVehicle1);

    }
}

