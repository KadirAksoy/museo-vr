package com.kadiraksoy.museo_vr.service;

import com.kadiraksoy.museo_vr.model.log.ErrorResponse;

public interface LogService {

    void logInfo(String message);
    void logError(ErrorResponse errorResponse);
}
