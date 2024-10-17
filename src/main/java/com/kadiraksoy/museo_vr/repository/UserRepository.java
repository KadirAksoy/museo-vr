package com.kadiraksoy.museo_vr.repository;

import com.kadiraksoy.museo_vr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}
