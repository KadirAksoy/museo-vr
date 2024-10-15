package com.kadiraksoy.museo_vr.repository;

import com.kadiraksoy.museo_vr.model.log.ErrorResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorResponseRepository extends JpaRepository<ErrorResponse, Long> {
}
