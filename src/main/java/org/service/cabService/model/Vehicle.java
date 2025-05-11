package org.service.cabService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.service.cabService.enums.VehicleStatus;

@Entity
@Table(name = "vehicle_Info")
@Getter
@Setter
public class Vehicle {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String vehicleType;

    @Column(unique = true, nullable = false)
    private String vehicleNumber;

    @Enumerated(EnumType.STRING)
    private VehicleStatus status;

    @Column(name = "fk_driver_Id", nullable = false)
    private Long driverId;

    private boolean isCurrentVehicle;
}
