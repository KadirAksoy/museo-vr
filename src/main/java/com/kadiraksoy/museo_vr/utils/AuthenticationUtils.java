package com.kadiraksoy.museo_vr.utils;

import com.kadiraksoy.museo_vr.service.impl.user.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthenticationUtils {

    // SecurityContext'ten kimlik doğrulama nesnesini getirir.
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    // Giriş yapan kullanıcının CustomUserDetails nesnesini getirir.
    public static CustomUserDetails getCurrentUserDetails() {
        return (CustomUserDetails) getAuthentication().getPrincipal();
    }

    // Giriş yapan kullanıcının ID'sini getirir.
    public static Long getCurrentUserId() {
        return getCurrentUserDetails().getUser().getId();
    }
}
