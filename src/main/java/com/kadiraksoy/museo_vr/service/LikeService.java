package com.kadiraksoy.museo_vr.service;

import com.kadiraksoy.museo_vr.model.Like;

import java.util.List;

public interface LikeService {

    void likeTravel(Long travelId, Long userId);
    int getLikesForTravel(Long travelId);
    void unlikeTravel(Long travelId, Long userId);
    //List<Like> getLikesForUser(Long userId);
    List<Like> getTop5MostLikedTravels();

}
