package com.kadiraksoy.museo_vr.exception;

import com.kadiraksoy.museo_vr.model.BaseEntity;
import com.kadiraksoy.museo_vr.model.log.ErrorResponse;

public class TravelNotFoundException extends BaseException {
    public TravelNotFoundException() {
        super(new ErrorResponse("TRAVEL_NOT_FOUND", "Travel not found" ));
    }
}
