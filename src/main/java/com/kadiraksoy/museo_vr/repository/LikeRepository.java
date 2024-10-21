package com.kadiraksoy.museo_vr.repository;

import com.kadiraksoy.museo_vr.model.Like;
import com.kadiraksoy.museo_vr.model.Travel;
import com.kadiraksoy.museo_vr.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {

    boolean existsByTravelAndUser(Travel travel, User user);
    Optional<Like> findByTravelAndUser(Travel travel, User user);
    int countByTravelId(Long travelId);
    List<Like> findAllByUserId(Long userId);
    List<Like> findAllByTravelId(Long travelId);



}
