package com.sagarrashinkar.controllers;

import com.sagarrashinkar.entities.User;
import com.sagarrashinkar.payloads.ApiResponse;
import com.sagarrashinkar.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> loginUser(@RequestBody Map<String, String> credentials){
        String username = credentials.get("username");
        String password = credentials.get("password");

        User user = userRepository.findByUserName(username);
        if (user != null && user.getPassword().equals(password)){
            return new ResponseEntity<>(new ApiResponse("User logged in successfully !!", true), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(new ApiResponse("Failed to login !!", false), HttpStatus.UNAUTHORIZED);
        }
    }
}
