package com.kadiraksoy.museo_vr.repository;

import com.kadiraksoy.museo_vr.model.Travel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TravelRepository extends JpaRepository<Travel, Long> {

    List<Travel> findByUserId(Long userId);
    @Query("SELECT t FROM Travel t LEFT JOIN t.likes l GROUP BY t.id ORDER BY COUNT(l.id) DESC")
    List<Travel> findTop10ByOrderByLikesDesc(Pageable pageable);

}
