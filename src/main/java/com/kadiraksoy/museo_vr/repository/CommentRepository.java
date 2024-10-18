package com.kadiraksoy.museo_vr.repository;

import com.kadiraksoy.museo_vr.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
