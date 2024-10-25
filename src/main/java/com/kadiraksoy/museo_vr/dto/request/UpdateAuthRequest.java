package com.kadiraksoy.museo_vr.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateAuthRequest {

    private String name;
    private String lastName;
    private String email;
    private String password;
    private LocalDateTime birthDate;
}
