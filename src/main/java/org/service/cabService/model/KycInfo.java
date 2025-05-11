package org.service.cabService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "driver_document")
@Getter
@Setter
public class KycInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fk_user_Id", nullable = false)
    private  Long userId;

    @Column(unique = true, nullable = false)
    private String adharNumber;

    private String adharName;
    private String adharImagePath;



    @Column(unique = true, nullable = false)
    private String panNumber;

    private String panName;
    private LocalDate dob;
    private String panImagePath;



}
