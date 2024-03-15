package com.Anime.App.service;

import com.Anime.App.exception.InvalidCredentialsException;
import com.Anime.App.model.AppUser;
import com.Anime.App.model.ResetToken;
import com.Anime.App.repository.ResetTokenRepository;
import com.Anime.App.repository.RoleRepository;
import com.Anime.App.repository.UserRepository;
import com.Anime.App.request.AuthRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResetTokenRepository resetTokenRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public static final int TOKEN_LENGTH=32;

    public  static String generateToken(){
        byte[] randomByte=new byte[TOKEN_LENGTH];
        new SecureRandom().nextBytes(randomByte);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomByte);
    }


    public AppUser login(AuthRequest authRequest) {
        AppUser appUser=userRepository.findByUsername(authRequest.getUsername())
                .orElseThrow(()->new InvalidCredentialsException("Username not found"));
        return appUser;
    }

    public AppUser register(AuthRequest authRequest) {
        AppUser appUser=new AppUser();
        appUser.setUsername(authRequest.getUsername());
        appUser.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));
        appUser.setEmail(authRequest.getEmail());
        appUser.setRoles(roleRepository.findByName(authRequest.getRole()));
        userRepository.save(appUser);
        return appUser;
    }


    public void createPasswordToken(AuthRequest authRequest) {
        AppUser appUser=userRepository.findByEmail(authRequest.getEmail()).orElseThrow(()->new InvalidCredentialsException("Email not found"));
        String token=generateToken();
        ResetToken resetToken=new ResetToken();
        resetToken.setToken(token);
        resetToken.setAppUser(appUser);
        resetToken.setExpiryDate(LocalDateTime.now().plusHours(24));
        resetTokenRepository.save(resetToken);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(appUser.getEmail());
        message.setSubject("Password reset token");
        message.setText("Temporary token for password reset => "+ resetToken.getToken());
        message.setFrom("kuttyvel152@gmail.com");
        javaMailSender.send(message);
    }

    public void resetPassword(AuthRequest authRequest) {
        ResetToken resetToken=resetTokenRepository.findByToken(authRequest.getToken());
        if(resetToken==null || resetToken.getExpiryDate().isBefore(LocalDateTime.now())){
            throw new InvalidCredentialsException("Invalid or expired token");
        }
        AppUser appUser=resetToken.getAppUser();
        appUser.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));
        userRepository.save(appUser);
        resetTokenRepository.delete(resetToken);
    }

}
