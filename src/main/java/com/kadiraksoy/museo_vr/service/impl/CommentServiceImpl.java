package com.kadiraksoy.museo_vr.service.impl;

import com.kadiraksoy.museo_vr.dto.request.CommentRequest;
import com.kadiraksoy.museo_vr.dto.response.CommentResponse;
import com.kadiraksoy.museo_vr.exception.CommentNotFoundException;
import com.kadiraksoy.museo_vr.exception.UserNotFoundException;
import com.kadiraksoy.museo_vr.exception.TravelNotFoundException;
import com.kadiraksoy.museo_vr.model.Comment;
import com.kadiraksoy.museo_vr.model.Travel;
import com.kadiraksoy.museo_vr.model.User;
import com.kadiraksoy.museo_vr.repository.CommentRepository;
import com.kadiraksoy.museo_vr.repository.TravelRepository;
import com.kadiraksoy.museo_vr.service.CommentService;
import com.kadiraksoy.museo_vr.service.UserService;
import com.kadiraksoy.museo_vr.utils.AuthenticationUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final TravelRepository travelRepository;

    @Override
    public CommentResponse addComment(Long travelId, CommentRequest commentRequest) {
        User user = getUserBySecurityContext();
        Travel travel = travelRepository.findById(travelId)
                .orElseThrow(TravelNotFoundException::new);

        Comment comment = Comment.builder()
                .content(commentRequest.getContent())
                .travel(travel)
                .user(user)
                .build();
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);

        return CommentResponse.builder()
                .id(comment.getId())
                .userId(comment.getUser().getId())
                .travelId(travelId)
                .content(commentRequest.getContent())
                .createdAt(comment.getCreatedAt())
                .updatedAt(comment.getUpdatedAt())
                .build();
    }

    @Override
    public List<CommentResponse> getCommentsForTravel(Long travelId) {
        Travel travel = travelRepository.findById(travelId).orElseThrow(TravelNotFoundException::new);

        List<Comment> comments = commentRepository.findByTravelId(travelId);
        return comments.stream()
                .map(comment -> CommentResponse.builder()
                        .id(comment.getId())
                        .userId(comment.getUser().getId())
                        .travelId(comment.getTravel().getId())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .updatedAt(comment.getUpdatedAt())
                        .build()
                )
                .toList();
    }

    @Override
    public void deleteComment(Long commentId) {
        User user = getUserBySecurityContext();
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new CommentNotFoundException();
        }
        commentRepository.delete(comment);

    }

    @Override
    public void updateComment(Long commentId, CommentRequest commentRequest) {
        User user = getUserBySecurityContext();  // Kullanıcı doğrulama
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if (!comment.getUser().getId().equals(user.getId())) {
            throw new IllegalStateException("You can only update your own comments.");
        }

        comment.setContent(commentRequest.getContent());
        comment.setUpdatedAt(LocalDateTime.now());

        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public List<CommentResponse> getCommentsForUser(Long userId) {
        List<Comment> comments = commentRepository.findByUserId(userId);

        return comments.stream()
                .map(comment -> CommentResponse.builder()
                        .id(comment.getId())
                        .userId(comment.getUser().getId())
                        .travelId(comment.getTravel().getId())
                        .content(comment.getContent())
                        .createdAt(comment.getCreatedAt())
                        .updatedAt(comment.getUpdatedAt())
                        .build()
                )
                .toList();
    }

    @Override
    public CommentResponse getCommentById(Long commentId) {
        return null;
    }


    private User getUserBySecurityContext() {
        Long userId = AuthenticationUtils.getCurrentUserId();
        User user = userService.getUserById(userId);

        if (user == null) {
            throw new UserNotFoundException("User not found");
        }
        return user;
    }
}
