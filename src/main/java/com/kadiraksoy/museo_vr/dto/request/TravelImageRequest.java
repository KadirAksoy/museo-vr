package com.kadiraksoy.museo_vr.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TravelImageRequest {

    private String content;
}
