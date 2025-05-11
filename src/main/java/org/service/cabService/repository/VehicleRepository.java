package org.service.cabService.repository;


import org.service.cabService.enums.VehicleStatus;
import org.service.cabService.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByDriverId(Long driverId);
    List<Vehicle> findByIsCurrentVehicleTrueAndStatus( VehicleStatus status);


}
