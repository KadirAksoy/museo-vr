package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.log.ErrorLog;

public class TravelNotFoundException extends BaseException {
    public TravelNotFoundException() {
        super(new ErrorLog("TRAVEL_NOT_FOUND", "Travel not found" ));
    }
}
