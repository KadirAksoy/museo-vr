package com.kadiraksoy.museo_vr.service;

import com.kadiraksoy.museo_vr.dto.request.CommentRequest;
import com.kadiraksoy.museo_vr.dto.response.CommentResponse;
import com.kadiraksoy.museo_vr.model.Comment;

import java.util.List;

public interface CommentService {

    CommentResponse addComment(Long travelId, CommentRequest commentRequest);
    List<CommentResponse> getCommentsForTravel(Long travelId);
    void deleteComment(Long commentId);
    void updateComment(Long commentId, CommentRequest commentRequest);
    List<CommentResponse> getCommentsForUser(Long userId);
    CommentResponse getCommentById(Long commentId);

}
