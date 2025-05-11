package org.cabService.cabService.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.cabService.cabService.enums.UserType;

@Getter
@Setter

@Table(name = "users")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String mobile;

    @Column(name = "otp")
    private String otp;

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private boolean isVerified;
}
