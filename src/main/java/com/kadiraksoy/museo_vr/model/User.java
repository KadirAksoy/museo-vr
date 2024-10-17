package com.kadiraksoy.museo_vr.model;

import com.kadiraksoy.museo_vr.model.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity {


    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String lastName;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean isActive;
    @Column(nullable = false)
    private LocalDateTime birthDate;
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;
    private String otp;
    private LocalDateTime otpGeneratedTime;


}
