package org.service.cabService.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.service.cabService.enums.UserType;
import org.service.cabService.model.User;
import org.service.cabService.repository.UserRepository;
import org.service.cabService.service.KycService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@RestController
@RequestMapping("/kyc")
public class KycController {

    @Autowired
    private KycService kycService;

    @Autowired
    private UserRepository userRepository;


    @PostMapping("/kyc/submit")
    public ResponseEntity<?> submitKyc(@Valid
            @RequestBody Long userId,
            @RequestBody @Pattern(regexp = "^[2-9]{1}[0-9]{3}\\\\s[0-9]{4}\\\\s[0-9]{4}$", message = "Please provide a valid 12-digit Aadhar number.") String adharNumber,
            @RequestBody String adharName,
            @RequestBody MultipartFile adharImage,
            @RequestBody @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Please provide a valid PAN number.") String panNumber,
            @RequestBody String panName,
            @RequestBody String dob,
            @RequestBody MultipartFile panImage
    ) {
        try {

            User user = userRepository.findById(userId)
                    .orElseThrow(() -> new Exception("User not found"));


            if (user.getUserType() != UserType.DRIVER) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("Only drivers are allowed to submit KYC");
            }

            LocalDate dateOfBirth = LocalDate.parse(dob);
            kycService.saveOrUpdateFullKyc(userId, adharNumber, adharName, adharImage, panNumber, panName, dateOfBirth, panImage);

            return ResponseEntity.ok("KYC submitted successfully");
        } catch (Exception e) {
            return  ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
