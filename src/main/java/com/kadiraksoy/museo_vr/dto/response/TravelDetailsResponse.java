package com.kadiraksoy.museo_vr.dto.response;



import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelDetailsResponse {

    private TravelResponse travelResponse;
    private List<TravelImageResponse> travelImageResponses;
}
