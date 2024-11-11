package com.kadiraksoy.museo_vr.service.impl;

import com.kadiraksoy.museo_vr.model.enums.LogLevel;
import com.kadiraksoy.museo_vr.model.log.ErrorLog;
import com.kadiraksoy.museo_vr.model.log.Log;
import com.kadiraksoy.museo_vr.repository.ErrorLogRepository;
import com.kadiraksoy.museo_vr.repository.LogRepository;
import com.kadiraksoy.museo_vr.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceImpl implements LogService {

    private final LogRepository logRepository;
    private final ErrorLogRepository errorLogRepository;

    public LogServiceImpl(LogRepository logRepository, ErrorLogRepository errorLogRepository) {
        this.logRepository = logRepository;
        this.errorLogRepository = errorLogRepository;
    }


    public void log(Log log) {
        logRepository.save(log);
    }

    public void logError(ErrorLog errorLogs) {
        Log errorLog = new Log(errorLogs.getMessage(), LogLevel.ERROR);
        errorLogRepository.save(errorLogs);
        log(errorLog);
    }
}