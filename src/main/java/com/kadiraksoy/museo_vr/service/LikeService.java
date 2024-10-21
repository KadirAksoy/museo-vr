package com.kadiraksoy.museo_vr.service;

import com.kadiraksoy.museo_vr.dto.response.LikeResponse;

import java.util.List;

public interface LikeService {

    void likeTravel(Long travelId, Long userId);
    int getLikesCountForTravel(Long travelId);
    void unlikeTravel(Long travelId, Long userId);
    List<LikeResponse> getLikesForUser(Long userId);
    List<LikeResponse> getLikesUserIdForTravel(Long travelId);


}
