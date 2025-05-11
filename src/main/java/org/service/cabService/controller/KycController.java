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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
            @RequestParam Long userId,
            @RequestParam @Pattern(regexp = "^[1-9][0-9]{11}$", message = "Please provide a valid 12-digit Aadhar number.") String adharNumber,
            @RequestParam String adharName,
            @RequestParam MultipartFile adharImage,
            @RequestParam @Pattern(regexp = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$", message = "Please provide a valid PAN number.") String panNumber,
            @RequestParam String panName,
            @RequestParam String dob,
            @RequestParam MultipartFile panImage
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
