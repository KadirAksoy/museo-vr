package com.kadiraksoy.museo_vr.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelRequest {

    private String title;
    private String description;
    private TravelImageRequest travelImageRequest;
}
