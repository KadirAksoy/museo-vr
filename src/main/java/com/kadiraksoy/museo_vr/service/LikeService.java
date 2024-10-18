package com.kadiraksoy.museo_vr.service;

public interface LikeService {

    void likeTravel(Long travelId, Long userId);
    int getLikesForTravel(Long travelId);
    void unlikeTravel(Long travelId, Long userId);

}
