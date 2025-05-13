package org.service.cabService.controller;

import jakarta.validation.Valid;
import org.service.cabService.enums.UserType;
import org.service.cabService.jwt.JwtUtil;
import org.service.cabService.model.User;
import org.service.cabService.repository.UserRepository;
import org.service.cabService.service.UserService;
import org.service.cabService.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
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

    @Autowired
    private JwtUtil jwtUtil;



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
    public ResponseEntity<?> verifyDriverOtp(@RequestParam String mobile, @RequestParam String otp) {
        try {
            String message = userService.verifyLoginOtp(mobile, otp);

            User user = userRepository.findByMobile(mobile);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found after verification.");
            }

            if (user.getUserType() != UserType.DRIVER) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: Not a driver.");
            }


            String token = jwtUtil.generateToken(user.getMobile(), user.getUserType().name());

            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            response.put("mobile", user.getMobile());
            response.put("token", token);

            return ResponseEntity.ok(response);
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

            User user = userRepository.findByMobile(mobile);
            if (user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found after verification.");
            }

            if (user.getUserType() != UserType.CUSTOMER) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied: Not a Customer.");
            }


            String token = jwtUtil.generateToken(user.getMobile(), user.getUserType().name());

            Map<String, Object> response = new HashMap<>();
            response.put("message", message);
            response.put("mobile", user.getMobile());
            response.put("token", token);

            return ResponseEntity.ok(response);
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
