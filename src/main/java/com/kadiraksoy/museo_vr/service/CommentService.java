package com.kadiraksoy.museo_vr.service;

import com.kadiraksoy.museo_vr.model.Comment;

import java.util.List;

public interface CommentService {

    Comment addComment(Long travelId, Comment comment);
    List<Comment> getCommentsForTravel(Long travelId);
    void deleteComment(Long commentId);
    void updateComment(Comment comment);
    List<Comment> getCommentsForUser(Long userId);


}
