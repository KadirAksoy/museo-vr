package com.kadiraksoy.museo_vr.repository;

import com.kadiraksoy.museo_vr.model.log.Log;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogRepository extends JpaRepository<Log, Long> {
}
