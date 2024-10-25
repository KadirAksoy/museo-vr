package com.kadiraksoy.museo_vr.controller;

import com.kadiraksoy.museo_vr.dto.request.TravelImageRequest;
import com.kadiraksoy.museo_vr.model.TravelImage;
import com.kadiraksoy.museo_vr.service.TravelImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@RestController
@RequestMapping("/api/travel-images")
@RequiredArgsConstructor
public class TravelImageController {

    private final TravelImageService travelImageService;


    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable Long id) {
        byte[] imageData = travelImageService.getImage(id);
        return ResponseEntity.ok(imageData);
    }

    @GetMapping("/view/{id}")
    public ResponseEntity<String> getImageAsBase64(@PathVariable Long id) {
        byte[] imageData = travelImageService.getImage(id);
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        return ResponseEntity.ok(base64Image);
    }
}
