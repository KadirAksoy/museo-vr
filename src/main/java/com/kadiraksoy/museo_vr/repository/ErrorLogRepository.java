package com.kadiraksoy.museo_vr.repository;

import com.kadiraksoy.museo_vr.model.log.ErrorLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
}
