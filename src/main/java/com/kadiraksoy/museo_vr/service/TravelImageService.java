package com.kadiraksoy.museo_vr.service;

import com.kadiraksoy.museo_vr.dto.request.TravelImageRequest;
import com.kadiraksoy.museo_vr.model.TravelImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface TravelImageService {

    TravelImage saveTravelImage(TravelImageRequest travelImageRequest, MultipartFile file) throws IOException;
    TravelImage updateTravelImage(Long id, MultipartFile file, String content) throws IOException;
    byte[] getImage(Long id);
}
