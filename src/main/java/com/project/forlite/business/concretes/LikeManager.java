package com.project.forlite.business.concretes;

import com.project.forlite.business.abstracts.LikeService;
import com.project.forlite.business.abstracts.PostService;
import com.project.forlite.business.abstracts.UserService;
import com.project.forlite.business.requests.CreateLikeRequest;
import com.project.forlite.business.responses.GetLikeResponse;
import com.project.forlite.business.rules.LikeBusinessRules;
import com.project.forlite.business.rules.PostBusinessRules;
import com.project.forlite.business.rules.UserBusinessRules;
import com.project.forlite.core.utilities.mappers.ModelMapperService;
import com.project.forlite.dataAccess.LikeRepository;
import com.project.forlite.entities.Like;
import com.project.forlite.entities.Post;
import com.project.forlite.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LikeManager implements LikeService {
    private LikeRepository likeRepository;
    private ModelMapperService modelMapperService;
    private UserBusinessRules userBusinessRules;
    private PostBusinessRules postBusinessRules;
    private LikeBusinessRules likeBusinessRules;

    @Override
    public List<GetLikeResponse> getAllLikes(Optional<Integer> userId, Optional<Integer> postId) {
        List<Like> likes;
        if (userId.isPresent() && postId.isPresent()){
            userBusinessRules.checkIfUserNotExists(userId.get());
            postBusinessRules.checkIfPostNotExists(postId.get());
            likes = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            userBusinessRules.checkIfUserNotExists(userId.get());
            likes = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            postBusinessRules.checkIfPostNotExists(postId.get());
            likes = likeRepository.findByPostId(postId.get());
        }else {
            likes = likeRepository.findAll();
        }
        return likes.stream().map(like -> modelMapperService.forResponse().map(like, GetLikeResponse.class)).toList();
    }

    @Override
    public GetLikeResponse getLikeById(int likeId) {
        likeBusinessRules.checkIfLikeNotExists(likeId);

        return modelMapperService.forResponse().map(likeRepository.findById(likeId).orElse(null), GetLikeResponse.class);
    }

    @Override
    public GetLikeResponse createLike(CreateLikeRequest createLikeRequest) {
        userBusinessRules.checkIfUserNotExists(createLikeRequest.getUserId());
        postBusinessRules.checkIfPostNotExists(createLikeRequest.getPostId());

        Like like = modelMapperService.forRequest().map(createLikeRequest, Like.class);
        likeRepository.save(like);

        return modelMapperService.forResponse().map(like, GetLikeResponse.class);
    }

    @Override
    public void deleteLikeById(int likeId) {
        likeBusinessRules.checkIfLikeNotExists(likeId);

        likeRepository.deleteById(likeId);
    }
}
