package org.service.cabService.controller;

import jakarta.validation.Valid;
import org.service.cabService.model.User;
import org.service.cabService.repository.UserRepository;
import org.service.cabService.service.UserService;
import org.service.cabService.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleService vehicleService;



    @PostMapping("/register/driver")
    public ResponseEntity<?> registerDriver(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        try {
            User registered = userService.registerDriver(user);
            return ResponseEntity.ok("Register OTP sent to mobile: " + registered.getMobile());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String mobile, @RequestParam String otp) {
        try {
            User verifiedUser = userService.verifyOtp(mobile, otp);
            return ResponseEntity.ok(Map.of("message" , "Driver Registered successfully:" ,
                    "mobile",  verifiedUser.getMobile()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @PostMapping("/login/driver")
    public ResponseEntity<?> loginDriver(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        try {
            User loggedIn = userService.loginDriver(user);
            return ResponseEntity.ok("Login OTP send to mobile: "  + loggedIn.getMobile());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/verify-login/driver")
    public ResponseEntity<?> verifyDriverOtp(@RequestParam String mobile, @RequestParam String otp ){
        try {
            String message = userService.verifyLoginOtp(mobile, otp);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    @PostMapping("/register/customer")
    public ResponseEntity<?> registerCustomer(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        try {
            User registered = userService.registerCustomer(user);
            return ResponseEntity.ok("Register OTP sent to mobile: " + registered.getMobile());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/verify-otp/customer")
    public ResponseEntity<?> verifyOtpCustomer(@RequestParam String mobile, @RequestParam String otp) {
        try {
            User verifiedUser = userService.verifyOtp(mobile, otp);
            return ResponseEntity.ok(Map.of("message" , "Customer Registered successfully:" ,
                    "mobile",  verifiedUser.getMobile()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }


    @PostMapping("/login/customer")
    public ResponseEntity<?> loginCustomer(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()){
            return ResponseEntity.badRequest().body(result.getFieldError().getDefaultMessage());
        }
        try {
            User loggedIn = userService.loginCustomer(user);
            return ResponseEntity.ok("Login OTP send to mobile: "  + loggedIn.getMobile());
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/verify-login/customer")
    public ResponseEntity<?> verifyCustomerOtp(@RequestParam String mobile, @RequestParam String otp ){
        try {
            String message = userService.verifyLoginCustomerOtp(mobile, otp);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/available-drivers")
    public ResponseEntity<?> getAvailableDrivers(@RequestHeader("Authorization") String token) {
        try {
            return ResponseEntity.ok(vehicleService.getAvailableDriversAndVehicles(token));
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

}
