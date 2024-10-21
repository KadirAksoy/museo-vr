package com.kadiraksoy.museo_vr.repository;

import com.kadiraksoy.museo_vr.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findByTravelId(Long travelId);
    List<Comment> findByUserId(Long userId);
}
