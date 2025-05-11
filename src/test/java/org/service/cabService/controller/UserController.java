package org.cabService.cabService.controller;

import org.cabService.cabService.model.User;
import org.cabService.cabService.repository.UserRepository;
import org.cabService.cabService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @PostMapping("/register/driver")
    public ResponseEntity<?> registerDriver(@RequestBody User user) {
        try {
            User registered = userService.registerDriver(user);
            return ResponseEntity.ok(registered);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @PostMapping("/login/driver")
    public ResponseEntity<?> loginDriver(@RequestBody User user) {
        try {
            User loggedIn = userService.loginDriver(user);
            return ResponseEntity.ok(loggedIn);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/register/customer")
    public ResponseEntity<?> registerCustomer(@RequestBody User user) {
        try {
            User registered = userService.registerCustomer(user);
            return ResponseEntity.ok(registered);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login/customer")
    public ResponseEntity<?> loginCustomer(@RequestBody User user){
        try {
            User loggedIn = userService.loginCustomer(user);
            return ResponseEntity.ok(loggedIn);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestParam String mobile, @RequestParam String otp) {
        try {
            User verifiedUser = userService.verifyOtp(mobile, otp);
            return ResponseEntity.ok(verifiedUser);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @GetMapping("/driver")
    public ResponseEntity<?> getDriverByToken(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "").trim();
            User driver = userService.getDriverByToken(token);
            return ResponseEntity.ok(driver);
        } catch (Exception e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }


    @GetMapping("/validate/driver")
    public ResponseEntity<?> validateDriver(@RequestHeader("Authorization") String authorizationHeader) {
        try {
            String token = authorizationHeader.replace("Bearer ", "").trim();
            userService.validateDriver(token);
            return ResponseEntity.ok("Driver token is valid.");
        } catch (Exception e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }


}
