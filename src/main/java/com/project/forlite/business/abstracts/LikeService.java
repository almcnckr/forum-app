package com.project.forlite.business.abstracts;

import com.project.forlite.business.requests.CreateLikeRequest;
import com.project.forlite.business.responses.GetLikeResponse;
import com.project.forlite.entities.Like;

import java.util.List;
import java.util.Optional;

public interface LikeService {
    List<GetLikeResponse> getAllLikes(Optional<Integer> userId, Optional<Integer> postId);

    GetLikeResponse getLikeById(int likeId);

    GetLikeResponse createLike(CreateLikeRequest createLikeRequest);

    void deleteLikeById(int likeId);
}
