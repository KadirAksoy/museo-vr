package com.kadiraksoy.museo_vr.service;

import com.kadiraksoy.museo_vr.model.log.ErrorResponse;
import com.kadiraksoy.museo_vr.model.log.Log;

public interface LogService {

    void log(Log log);
    void logError(ErrorResponse errorResponse);
}
