package com.Anime.App.controller;

import com.Anime.App.model.AppUser;
import com.Anime.App.request.AuthRequest;
import com.Anime.App.response.ApiResponse;
import com.Anime.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private ApiResponse apiResponse;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody AuthRequest authRequest){
        AppUser loginUser=userService.login(authRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(loginUser);
        return  new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody AuthRequest authRequest){
        AppUser registerUser=userService.register(authRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(registerUser);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/passwordReset/request")
    public ResponseEntity<ApiResponse> requestPasswordReset(@RequestBody AuthRequest authRequest){
        userService.createPasswordToken(authRequest);
        apiResponse.setData("Password reset email sent successfully");
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PostMapping("/passwordReset/reset")
    public ResponseEntity<ApiResponse> resetPassword(@RequestBody AuthRequest authRequest){
        userService.resetPassword(authRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData("password reset successfully");
        apiResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
