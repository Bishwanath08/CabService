package org.service.cabService.service;

import org.service.cabService.enums.UserType;
import org.service.cabService.enums.VehicleStatus;
import org.service.cabService.model.KycInfo;
import org.service.cabService.model.User;
import org.service.cabService.model.Vehicle;
import org.service.cabService.repository.KycRepository;
import org.service.cabService.repository.UserRepository;
import org.service.cabService.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KycRepository kycRepository;

    public Vehicle addVehicle(Long driverId, String vehicleType, String vehicleNumber, VehicleStatus status) throws  Exception{
        User driver = userRepository.findById(driverId).orElseThrow(() -> new Exception("Driver not found"));

        if (driver.getUserType() != UserType.DRIVER){
            throw new Exception("Only driver can add vehicles");
        }

        Vehicle vehicle = new Vehicle();
        vehicle.setDriverId(driver.getId());
        vehicle.setVehicleType(vehicleType);
        vehicle.setVehicleNumber(vehicleNumber);
        vehicle.setStatus(status);
        vehicle.setCurrentVehicle(false);

        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> getDriverVehicles(Long driverId){
        return vehicleRepository.findByDriverId(driverId);
    }

    public Vehicle updateVehicle(Long vehicleId, String type, String number, VehicleStatus status) throws Exception{
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(()-> new Exception("Vehicle not found"));

        vehicle.setVehicleType(type);
        vehicle.setVehicleNumber(number);
        vehicle.setStatus(status);

        return vehicleRepository.save(vehicle);
    }

    public void deleteVehicle(Long vehicleId) {
        vehicleRepository.deleteById(vehicleId);
    }

    public void changeVehicleStatus(Long vehicleId, VehicleStatus status) throws  Exception{
        Vehicle vehicle = vehicleRepository.findById(vehicleId).orElseThrow(()-> new Exception("Vehicle not found"));

        vehicle.setStatus(status);
        vehicleRepository.save(vehicle);
    }

    public void assignCurrentVehicle(Long driverId, Long vehicleId) throws Exception{
        List<Vehicle> vehiclesList = vehicleRepository.findByDriverId(driverId);
        for (Vehicle v : vehiclesList) {
            v.setCurrentVehicle(v.getId().equals(vehicleId));
        }
        vehicleRepository.saveAll(vehiclesList);
    }

    public List<Map<String, Object>>  getAvailableDriversAndVehicles(String token) throws  Exception{

        User user = userRepository.findByToken(token);

        if (user.getUserType() != UserType.CUSTOMER) {
            throw  new Exception("Access denied. Only customer can view drivers.");
        }

        List<Vehicle> vehicles = vehicleRepository.findByIsCurrentVehicleTrueAndStatus(VehicleStatus.ACTIVE);
        List<Map<String, Object>> response = new ArrayList<>();

        for (Vehicle v : vehicles) {

            KycInfo kyc = kycRepository.findByUserId(v.getDriverId());
            if (kyc == null){
                System.out.println("Driver ID " + v.getDriverId() + " skipped: No KYC found.");
                continue;
            }

            Map<String, Object> info = new HashMap<>();
            info.put("driverName", kyc.getAdharName());
            info.put("vehicleType", v.getVehicleType());
            info.put("vehicleNumber", v.getVehicleNumber());
            response.add(info);
        }
        return response;
    }
}
