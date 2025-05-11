package org.cabService.cabService.service;

import org.cabService.cabService.enums.UserType;
import org.cabService.cabService.model.User;
import org.cabService.cabService.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User registerDriver(User user) throws Exception {
        User existingUser = userRepository.findByMobile(user.getMobile());

        if (existingUser != null) {
            throw new Exception("Mobile number already registered");
        }

        user.setUserType(UserType.DRIVER);
        user.setOtp(generateOTP(4));
        user.setVerified(false);
        return userRepository.save(user);
    }


    public User loginDriver(User user) throws Exception{
        User user1 = userRepository.findByMobile(user.getMobile());
        if (user!=null) {
            throw new Exception("Driver not found! ");
        }
        String token = UUID.randomUUID()+"_"+UUID.randomUUID();
        user.setMobile(user.getMobile());
        user.setToken(token);
        user.setOtp(generateOTP(4));
        userRepository.save(user);
        return user;
    }

    public User getDriverByToken(String token) throws Exception {
        User user = userRepository.findByToken(token);
        if (user==null) {
            throw new RuntimeException("Invalid token or user not found ");
        }
        return user;
    }

    public void validateDriver(String token) throws  Exception{
        User user = userRepository.findByToken(token);
        if (user== null || user.getUserType() !=UserType.DRIVER) {
            throw new Exception("Access Denied: Driver privileges required. ") ;
        }
    }

    public User registerCustomer(User user) throws  Exception{
        User user1  = userRepository.findByMobile(user.getMobile());
        if (user!=null) {
            throw new Exception("Mobile already register");
        }
        user= new User();
        user.setMobile(user.getMobile());
        user.setUserType(UserType.CUSTOMER);
        user.setOtp(generateOTP(4));

        return userRepository.save(user);
    }

    public User loginCustomer(User user) throws Exception {
        User user1 = userRepository.findByMobile(user.getMobile());

        if (user==null) {
            throw new Exception("User Not found! ");
        }

        String token = UUID.randomUUID()+"_"+UUID.randomUUID();

        user.setMobile(user.getMobile());
        user.setToken(token);
        user.setOtp(generateOTP(4));
        return user1;
    }

    public String generateOTP(int length) {
        String digits = "0123456789";
        StringBuilder otp = new StringBuilder(4);
        Random random = new Random();
        for (int i = 0; i < length; i++){
            otp.append(digits.charAt(random.nextInt(digits.length())));
        }
        System.out.println("YOUR OTP IS ::::: " + otp);
        return otp.toString();
    }

    public User verifyOtp(String mobile, String otp) {
        Optional<User> userOtp = Optional.ofNullable(userRepository.findByMobile(mobile));

        if (userOtp.isPresent()) {
            User user = userOtp.get();

            if (user.getOtp().equals(otp)){

            }
        } else {
            throw  new RuntimeException("User not Found");
        }
        return null;
    }
}
