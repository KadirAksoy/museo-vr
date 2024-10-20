package com.kadiraksoy.museo_vr.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kadiraksoy.museo_vr.dto.request.TravelImageRequest;
import com.kadiraksoy.museo_vr.dto.request.TravelRequest;
import com.kadiraksoy.museo_vr.dto.response.TravelDetailsResponse;
import com.kadiraksoy.museo_vr.dto.response.TravelResponse;
import com.kadiraksoy.museo_vr.exception.TravelNotFoundException;
import com.kadiraksoy.museo_vr.exception.UserNotFoundException;
import com.kadiraksoy.museo_vr.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/travel")
@RequiredArgsConstructor
public class TravelController {

    private final TravelService travelService;

    @PostMapping("/create")
    public ResponseEntity<TravelResponse> createTravel(
            @RequestParam("travelRequest") String travelRequestJson,
            @RequestParam("file") MultipartFile file) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TravelRequest travelRequest = objectMapper.readValue(travelRequestJson, TravelRequest.class);
        TravelResponse travelResponse = travelService.createTravel(travelRequest, file);
        return new ResponseEntity<>(travelResponse, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TravelResponse> updateTravel(
            @PathVariable Long id,
            @RequestParam("travelRequest") String travelRequestJson,
            @RequestParam(value = "file", required = false) MultipartFile file) throws IOException {
      ObjectMapper objectMapper = new ObjectMapper();
      TravelRequest travelRequest = objectMapper.readValue(travelRequestJson, TravelRequest.class);
      TravelResponse travelResponse = travelService.updateTravel(id, travelRequest, file);
      return ResponseEntity.ok(travelResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TravelDetailsResponse> getTravelById(@PathVariable Long id) {
        TravelDetailsResponse travelDetailsResponse = travelService.getTravelById(id);
        return ResponseEntity.ok(travelDetailsResponse);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TravelResponse>> getTravelsByUserId(@PathVariable Long userId) {
        List<TravelResponse> travelResponses = travelService.getTravelsByUserId(userId);
        return ResponseEntity.ok(travelResponses);
    }

    @GetMapping("/all")
    public ResponseEntity<List<TravelResponse>> getAllTravels() {
        List<TravelResponse> travels = travelService.getTravels();
        return ResponseEntity.ok(travels);
    }

    @GetMapping("/sorted/likes")
    public ResponseEntity<List<TravelResponse>> getTravelsSortedByLikes() {
        List<TravelResponse> travels = travelService.getTravelsSortedByLikes();
        return ResponseEntity.ok(travels);
    }

    @PostMapping("/{id}/add-image")
    public ResponseEntity<TravelResponse> addImageToTravel(
            @PathVariable Long id,
            @RequestParam("image") MultipartFile image,
            @RequestParam("content") String contentJson) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TravelImageRequest content = objectMapper.readValue(contentJson, TravelImageRequest.class);
        TravelResponse travelResponse = travelService.addImagesToTravel(id, image, content);
        return ResponseEntity.ok(travelResponse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTravel(@PathVariable Long id) {
        travelService.deleteTravel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @DeleteMapping("/{travelId}/delete-image/{imageId}")
    public ResponseEntity<Void> deleteImageFromTravel(@PathVariable Long travelId, @PathVariable Long imageId) {
        travelService.deleteTravelImagesFromTravel(travelId, imageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{travelId}/update-image/{imageId}")
    public ResponseEntity<TravelResponse> updateImageToTravel(
            @PathVariable Long travelId,
            @PathVariable Long imageId,
            @RequestParam("image") MultipartFile image,
            @RequestParam("content") String contentJson
    ) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        TravelImageRequest content = objectMapper.readValue(contentJson, TravelImageRequest.class);
        TravelResponse travelResponse = travelService.updateImagesToTravel(travelId, imageId, image, content);
        return ResponseEntity.ok(travelResponse);
    }
}
