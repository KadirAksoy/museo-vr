package com.kadiraksoy.museo_vr.service;

import com.kadiraksoy.museo_vr.model.User;

import java.util.List;

public interface UserService {

    User save(User user);
    User updateUser(Long id, User updatedUser);
    User getUserById(Long id);
    User getUserByEmail(String email);
    List<User> getAllUsers();
    void deleteUser(Long id);


}
