package com.project.forlite.business.concretes;

import com.project.forlite.business.abstracts.LikeService;
import com.project.forlite.business.abstracts.PostService;
import com.project.forlite.business.abstracts.UserService;
import com.project.forlite.business.requests.CreateLikeRequest;
import com.project.forlite.business.responses.GetLikeResponse;
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
    private UserService userService;
    private PostService postService;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetLikeResponse> getAllLikes(Optional<Integer> userId, Optional<Integer> postId) {
        List<Like> likes;
        if (userId.isPresent() && postId.isPresent()){
            likes = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            likes = likeRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            likes = likeRepository.findByPostId(postId.get());
        }else {
            likes = likeRepository.findAll();
        }
        return likes.stream().map(like -> modelMapperService.forResponse().map(like, GetLikeResponse.class)).toList();
    }

    @Override
    public GetLikeResponse getLikeById(int likeId) {
        return modelMapperService.forResponse().map(likeRepository.findById(likeId).orElse(null), GetLikeResponse.class);
    }

    @Override
    public Like createLike(CreateLikeRequest createLikeRequest) {
        User user = modelMapperService.forResponse().map(userService.getUserById(createLikeRequest.getUserId()), User.class);
        Post post = modelMapperService.forResponse().map(postService.getPostById(createLikeRequest.getPostId()), Post.class);
        if (user == null || post == null){
            return null;
        }
        Like like = new Like();
        like.setUser(user);
        like.setPost(post);
        return likeRepository.save(like);
    }

    @Override
    public void deleteLikeById(int likeId) {
        likeRepository.deleteById(likeId);
    }
}
