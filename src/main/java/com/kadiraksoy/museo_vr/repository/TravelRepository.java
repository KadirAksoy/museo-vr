package com.kadiraksoy.museo_vr.repository;

import com.kadiraksoy.museo_vr.model.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {

    List<Travel> findByUserId(Long userId);
    List<Travel> findTop10ByOrderByLikesDesc();

}
