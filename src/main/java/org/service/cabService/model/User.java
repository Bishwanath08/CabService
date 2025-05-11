package org.service.cabService.model;

import jakarta.persistence.*;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import org.service.cabService.enums.UserType;

import java.time.LocalDateTime;


@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(unique = true, nullable = false)
    @Pattern(regexp = "^[6-9][0-9]{9}$", message = "Please provide valid mobile number.")
    private String mobile;


    @Column(name = "otp")
    private String otp;

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type")
    private UserType userType;

    private boolean isVerified;

    private LocalDateTime otpGeneratedAt;


}

