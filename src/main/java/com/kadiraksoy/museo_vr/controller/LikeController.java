package com.kadiraksoy.museo_vr.controller;

import com.kadiraksoy.museo_vr.dto.response.LikeResponse;
import com.kadiraksoy.museo_vr.model.Like;
import com.kadiraksoy.museo_vr.model.User;
import com.kadiraksoy.museo_vr.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    // 1. Seyahati beğenmek için endpoint
    @PostMapping("/travel/{travelId}")
    public ResponseEntity<String> likeTravel(@PathVariable Long travelId) {
        likeService.likeTravel(travelId, null);
        return ResponseEntity.status(HttpStatus.CREATED).body("Travel liked successfully.");
    }

    @DeleteMapping("/travel/{travelId}")
    public ResponseEntity<String> unlikeTravel(@PathVariable Long travelId) {
        likeService.unlikeTravel(travelId, null);
        return ResponseEntity.ok("Travel unliked successfully.");
    }


    @GetMapping("/travel/{travelId}/count")
    public ResponseEntity<Integer> getLikesForTravel(@PathVariable Long travelId) {
        int likeCount = likeService.getLikesCountForTravel(travelId);
        return ResponseEntity.ok(likeCount);
    }


    @GetMapping("/user/{userId}")
    public ResponseEntity<List<LikeResponse>> getLikesForUser(@PathVariable Long userId) {
        List<LikeResponse> likes = likeService.getLikesForUser(userId);
        return ResponseEntity.ok(likes);
    }
    @GetMapping("/travel/{travelId}")
    public ResponseEntity<List<LikeResponse>> getLikesUserIdForTravel(@PathVariable Long travelId) {
        List<LikeResponse> likes = likeService.getLikesUserIdForTravel  (travelId);
        return ResponseEntity.ok(likes);
    }
}
