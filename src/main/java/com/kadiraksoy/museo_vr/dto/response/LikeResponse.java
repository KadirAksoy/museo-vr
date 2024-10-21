package com.kadiraksoy.museo_vr.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LikeResponse {

    private Long travelId;
    private Long userId;

}
