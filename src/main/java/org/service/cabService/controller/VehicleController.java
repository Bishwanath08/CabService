package org.service.cabService.controller;

import org.service.cabService.enums.VehicleStatus;
import org.service.cabService.model.Vehicle;
import org.service.cabService.service.KycService;
import org.service.cabService.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private KycService kycService;

    private boolean isKycComplete(Long driverId) {
        return kycService.isKycComplete(driverId);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addVehicle(@RequestParam Long driverId,
                                        @RequestParam String vehicleType,
                                        @RequestParam String vehicleNumber,
                                        @RequestParam VehicleStatus status) {

        if (!isKycComplete(driverId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Complete KYC before adding vehicle.");
        }
        try {
            Vehicle vehicle = vehicleService.addVehicle(driverId, vehicleType, vehicleNumber, status);
            return ResponseEntity.ok(vehicle);
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("list/{driverId}")
    public ResponseEntity<?> listVehicles(@PathVariable Long driverId) {
        return ResponseEntity.ok(vehicleService.getDriverVehicles(driverId));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateVehicle(@PathVariable Long id,
                                           @RequestParam Long driverId,
                                           @RequestParam String vehicleType,
                                           @RequestParam String  vehicleNumber,
                                           @RequestParam VehicleStatus status) {


        if (!isKycComplete(driverId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("KYC required to update vehicle.");
        }
        try {
            Vehicle updated = vehicleService.updateVehicle(id, vehicleType,vehicleNumber,status);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {

        vehicleService.deleteVehicle(id);
        return ResponseEntity.ok("Vehicle deleted successfully");
    }

    @PutMapping("/status/{id}")
    public  ResponseEntity<?> changeStatus(@PathVariable Long id,
                                           @RequestParam VehicleStatus status,
                                           @RequestParam Long driverId) {
        if (!isKycComplete(driverId)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("KYC required to update vehicle.");
        }
        try {
            vehicleService.changeVehicleStatus(id, status);
            return ResponseEntity.ok("Vehicle status updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/set-current")
    public ResponseEntity<?> setCurrentVehicle(@RequestParam Long driverId,
                                               @RequestParam Long vehicleId) {

        try {
            vehicleService.assignCurrentVehicle(driverId, vehicleId);
            return ResponseEntity.ok("Current vehicle assigned.");
        } catch ( Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
