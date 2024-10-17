package com.kadiraksoy.museo_vr.service.impl.user;

import com.kadiraksoy.museo_vr.exception.EmailNotFoundException;
import com.kadiraksoy.museo_vr.model.User;
import com.kadiraksoy.museo_vr.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws EmailNotFoundException {
        User user = userService.getUserByEmail(email);
        if (user == null) {
            throw new EmailNotFoundException( email);
        }
        return new CustomUserDetails(user);
    }
}
