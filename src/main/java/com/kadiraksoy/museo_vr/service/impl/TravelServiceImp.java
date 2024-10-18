package com.kadiraksoy.museo_vr.service.impl;

import com.kadiraksoy.museo_vr.model.Travel;
import com.kadiraksoy.museo_vr.service.TravelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TravelServiceImp implements TravelService {
    @Override
    public Travel createTravel(Travel travel) {
        return null;
    }

    @Override
    public Travel updateTravel(Travel travel) {
        return null;
    }

    @Override
    public List<Travel> getTravels() {
        return List.of();
    }

    @Override
    public Travel getTravelById(Long id) {
        return null;
    }

    @Override
    public List<Travel> getTravelsByUserId(Long userId) {
        return List.of();
    }

    @Override
    public List<Travel> getTravelsSortedByLikes() {
        return List.of();
    }

    @Override
    public Travel addImagesToTravel(Long travelId, List<MultipartFile> images) {
        return null;
    }

    @Override
    public void deleteTravel(Long id) {

    }
}
