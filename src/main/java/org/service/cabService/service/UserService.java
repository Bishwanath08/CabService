package org.service.cabService.service;

import org.service.cabService.enums.UserType;
import org.service.cabService.jwt.JwtUtil;
import org.service.cabService.model.User;
import org.service.cabService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;


    public User registerDriver(User user) throws Exception {
        User existingUser = userRepository.findByMobile(user.getMobile());

        if (existingUser != null) {
            if (existingUser.isVerified()) {
                throw new Exception("Mobile number already registered");
            }

            generateOTP(4, existingUser);
            return userRepository.save(existingUser);
        }

        user.setUserType(UserType.DRIVER);
        generateOTP(4, user);
        user.setVerified(false);
        return userRepository.save(user);
    }


    public User loginDriver(User user) throws Exception{
        User existingUser = userRepository.findByMobile(user.getMobile());
        if (existingUser == null || existingUser.getUserType() != UserType.DRIVER) {
            throw new Exception("Driver not found! ");
        }

        String otp = generateOTP(4, existingUser);
        existingUser.setOtp(otp);
        userRepository.save(existingUser);

        System.out.println("Login OTP for " + existingUser.getMobile() + " : " + otp);

        return existingUser;
    }

    public String verifyLoginOtp(String mobile, String otp) throws Exception{

        User user = userRepository.findByMobile(mobile);

        if (user == null || user.getUserType() != UserType.DRIVER) {
            throw new Exception("Driver not found. ");
        }
        if (user.getOtp() == null || !otp.equals(user.getOtp())) {
            throw new Exception("Invalid OTP.");
        }
        if (user.getOtpGeneratedAt() == null || Duration.between(user.getOtpGeneratedAt(), LocalDateTime.now()).toMinutes() > 5){
            throw new Exception("OTP expired.Please request a new one.");
        }

        String token = jwtUtil.generateToken(user.getMobile(), user.getUserType().name());

        user.setToken(token);
        user.setOtp(null);
        user.setVerified(true);
        user.setOtpGeneratedAt(null);
        userRepository.save(user);

        return "Login successful:-";
    }

    public User registerCustomer(User user) throws Exception {
        User existingUser = userRepository.findByMobile(user.getMobile());

        if (existingUser != null) {
            if (existingUser.isVerified()) {
                throw new Exception("Mobile number already registered");
            }

            generateOTP(4, existingUser);
            return userRepository.save(existingUser);
        }

        user.setUserType(UserType.CUSTOMER);
        generateOTP(4, user);
        user.setVerified(false);
        return userRepository.save(user);
    }


    public User loginCustomer(User user) throws Exception{
        User existingUser = userRepository.findByMobile(user.getMobile());
        if (existingUser == null || existingUser.getUserType() != UserType.CUSTOMER) {
            throw new Exception("Customer not found! ");
        }

        String otp = generateOTP(4, existingUser);
        existingUser.setOtp(otp);
        userRepository.save(existingUser);

        System.out.println("Login OTP for " + existingUser.getMobile() + " : " + otp);

        return existingUser;
    }

    public String verifyLoginCustomerOtp(String mobile, String otp) throws Exception{
        User user = userRepository.findByMobile(mobile);

        if (user == null || user.getUserType() != UserType.CUSTOMER) {
            throw new Exception("Customer not found. ");
        }
        if (user.getOtp() == null || !otp.equals(user.getOtp())) {
            throw new Exception("Invalid OTP.");
        }
        if (user.getOtpGeneratedAt() == null || Duration.between(user.getOtpGeneratedAt(), LocalDateTime.now()).toMinutes() > 5){
            throw new Exception("OTP expired.Please request a new one.");
        }

        String token = jwtUtil.generateToken(user.getMobile(), user.getUserType().name());
        user.setToken(token);
        user.setOtp(null);
        user.setVerified(true);
        user.setOtpGeneratedAt(null);
        userRepository.save(user);

        return "Login successful:- ";
    }


    public String generateOTP(int length, User user) {
        String digits = "0123456789";
        StringBuilder otp = new StringBuilder(4);
        Random random = new Random();

        for (int i = 0; i < length; i++){
            otp.append(digits.charAt(random.nextInt(digits.length())));
        }

        String generatedOtp = otp.toString();
        user.setOtp(generatedOtp);
        user.setOtpGeneratedAt(LocalDateTime.now());
        user.setVerified(false);

        System.out.println("YOUR OTP IS ::::: " + generatedOtp);
        return otp.toString();
    }

    public User verifyOtp(String mobile, String otp) throws Exception {
        User user = userRepository.findByMobile(mobile);
        if (user == null) {
            throw new Exception("User not found with this mobile number");
        }

        if (user.isVerified()){
            throw new Exception("User already verified.");
        }

        if (!otp.equals(user.getOtp())){
            throw new Exception("Invalid OTP");
        }

        if (user.getOtpGeneratedAt() == null || Duration.between(user.getOtpGeneratedAt(), LocalDateTime.now()).toMinutes() > 5){
            throw new Exception("OTP expired.Please request a new one.");
        }

        user.setVerified(true);
        user.setOtp(null);
        user.setOtpGeneratedAt(null);

        return userRepository.save(user);
    }
}
