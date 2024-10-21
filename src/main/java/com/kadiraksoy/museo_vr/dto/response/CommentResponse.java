package com.kadiraksoy.museo_vr.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse {

    private Long id;
    private Long userId;
    private Long travelId;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
