package com.kadiraksoy.museo_vr.dto.response;

import jakarta.persistence.Lob;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelImageResponse {

    private Long id;
    private String name;
    private String type;
    private String content;

}

