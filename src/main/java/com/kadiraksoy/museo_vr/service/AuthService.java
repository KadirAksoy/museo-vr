package com.kadiraksoy.museo_vr.service;

import com.kadiraksoy.museo_vr.dto.request.LoginRequest;
import com.kadiraksoy.museo_vr.dto.request.RegisterRequest;
import com.kadiraksoy.museo_vr.dto.request.UpdateAuthRequest;
import com.kadiraksoy.museo_vr.dto.response.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse login(LoginRequest loginRequest);
    String register(RegisterRequest registerRequest);
    String verifyOtp(String email, String otp);
    String regenerateOtp(String email);
    String updateAuth(UpdateAuthRequest updateAuthRequest);

}
