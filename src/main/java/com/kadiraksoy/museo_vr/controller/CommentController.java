package com.kadiraksoy.museo_vr.controller;

import com.kadiraksoy.museo_vr.dto.request.CommentRequest;
import com.kadiraksoy.museo_vr.dto.response.CommentResponse;
import com.kadiraksoy.museo_vr.model.Comment;
import com.kadiraksoy.museo_vr.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.web.JsonPath;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{travelId}/add")
    public ResponseEntity<CommentResponse> addComment(@PathVariable Long travelId, @RequestBody CommentRequest commentRequest) {
        CommentResponse commentResponse = commentService.addComment(travelId, commentRequest);
        return ResponseEntity.ok(commentResponse);
    }

    @GetMapping("/{travelId}")
    public ResponseEntity<List<CommentResponse>> getComments(@PathVariable Long travelId) {
        List<CommentResponse> comments = commentService.getCommentsForTravel(travelId);
        return ResponseEntity.ok(comments);
    }

    @DeleteMapping("/{commentId}/delete")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{commentId}/update")
    public ResponseEntity<Void> updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentRequest commentRequest) {
        commentService.updateComment(commentId, commentRequest);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CommentResponse>> getUserComments(@PathVariable Long userId) {
        List<CommentResponse> comments = commentService.getCommentsForUser(userId);
        return ResponseEntity.ok(comments);
    }

}
