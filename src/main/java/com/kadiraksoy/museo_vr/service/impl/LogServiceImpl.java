package com.kadiraksoy.museo_vr.service.impl;

import com.kadiraksoy.museo_vr.model.enums.LogLevel;
import com.kadiraksoy.museo_vr.model.log.ErrorResponse;
import com.kadiraksoy.museo_vr.model.log.Log;
import com.kadiraksoy.museo_vr.repository.ErrorResponseRepository;
import com.kadiraksoy.museo_vr.repository.LogRepository;
import com.kadiraksoy.museo_vr.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final ErrorResponseRepository errorResponseRepository;

    public LogServiceImpl(LogRepository logRepository, ErrorResponseRepository errorResponseRepository) {
        this.logRepository = logRepository;
        this.errorResponseRepository = errorResponseRepository;
    }


    public void logInfo(String message) {
        Log log = new Log(message, LogLevel.INFO);
        logRepository.save(log);
    }

    public void logError(ErrorResponse errorResponse) {
        Log errorLog = new Log(errorResponse.getMessage(), LogLevel.ERROR);

        errorResponseRepository.save(errorResponse);
        logRepository.save(errorLog);
    }
}