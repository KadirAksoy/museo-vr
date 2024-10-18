package com.kadiraksoy.museo_vr.service.impl;

import com.kadiraksoy.museo_vr.dto.request.LoginRequest;
import com.kadiraksoy.museo_vr.dto.request.RegisterRequest;
import com.kadiraksoy.museo_vr.dto.response.AuthenticationResponse;
import com.kadiraksoy.museo_vr.exception.EmailNotSendException;
import com.kadiraksoy.museo_vr.exception.UserAlreadyExistException;
import com.kadiraksoy.museo_vr.exception.UserNotActiveException;
import com.kadiraksoy.museo_vr.exception.UserNotFoundException;
import com.kadiraksoy.museo_vr.model.User;
import com.kadiraksoy.museo_vr.model.enums.Role;
import com.kadiraksoy.museo_vr.security.JwtService;
import com.kadiraksoy.museo_vr.service.AuthService;
import com.kadiraksoy.museo_vr.service.UserService;
import com.kadiraksoy.museo_vr.service.impl.user.CustomUserDetails;
import com.kadiraksoy.museo_vr.utils.EmailUtil;
import com.kadiraksoy.museo_vr.utils.OtpUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final OtpUtil otpUtil;
    private final EmailUtil emailUtil;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        var user = userService.getUserByEmail(loginRequest.getEmail());
        if(!user.isActive()){
            throw new UserNotFoundException(loginRequest.getEmail());
        }
        String jwtToken = jwtService.generateToken(new CustomUserDetails(user));

        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    @Override
    public String register(RegisterRequest registerRequest) {
        User existingUser = userService.getUserByEmail(registerRequest.getEmail());
        if(existingUser != null){
            if(existingUser.isActive()){
                throw new UserAlreadyExistException(registerRequest.getEmail());
            }
            throw new UserNotActiveException();
        }
        String otp = otpUtil.generateOtp();
        try{
            //emailUtil.sendOtpEmail(registerRequest.getEmail(), otp);
        }
        catch (Exception e){
           throw new EmailNotSendException(registerRequest.getEmail());
        }
        User user = User.builder()
                .name(registerRequest.getName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .birthDate(registerRequest.getBirthDate())
                .isActive(false)
                .roles(Set.of(Role.ROLE_USER))
                .otp(otp)
                .otpGeneratedTime(LocalDateTime.now())
                .build();
        userService.save(user);
        return "User registered successfully. OTP has been sent to your email";
    }

    @Override
    public String verifyOtp(String email, String otp) {
        User user = userService.getUserByEmail(email);
        if(user == null){
            throw new UserNotFoundException(email);
        }
        if (user.getOtp().equals(otp) && Duration.between(user.getOtpGeneratedTime(),
                LocalDateTime.now()).getSeconds() < (1 * 60)) {
            user.setActive(true);
            userService.save(user);
            return "OTP verified you can login";
        }
        return "Please regenerate otp and try again";
    }

    public String regenerateOtp(String email) {
        User user = userService.getUserByEmail(email);
        if(user == null){
            throw new UserNotFoundException(email);
        }
        String otp = otpUtil.generateOtp();
        try {
            //emailUtil.sendOtpEmail(email, otp);
        } catch (Exception e) {
            throw new EmailNotSendException(email);
        }
        user.setOtp(otp);
        user.setOtpGeneratedTime(LocalDateTime.now());
        userService.save(user);
        return "Email sent... please verify account within 1 minute";
    }


}
