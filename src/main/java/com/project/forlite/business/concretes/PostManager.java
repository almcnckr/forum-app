package com.project.forlite.business.concretes;

import com.project.forlite.business.abstracts.PostService;
import com.project.forlite.business.requests.CreatePostRequest;
import com.project.forlite.business.requests.UpdatePostRequest;
import com.project.forlite.business.responses.GetPostResponse;
import com.project.forlite.business.rules.PostBusinessRules;
import com.project.forlite.business.rules.UserBusinessRules;
import com.project.forlite.core.utilities.mappers.ModelMapperService;
import com.project.forlite.dataAccess.PostRepository;
import com.project.forlite.entities.Post;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PostManager implements PostService {
    private PostRepository postRepository;
    private ModelMapperService modelMapperService;
    private UserBusinessRules userBusinessRules;
    private PostBusinessRules postBusinessRules;

    @Override
    public List<GetPostResponse> getAllPosts(Optional<Integer> userId) {
        List<Post> posts;
        if (userId.isPresent()) {
            posts = postRepository.findByUserId(userId);
            return posts.stream()
                    .map(post -> modelMapperService.forResponse().map(post, GetPostResponse.class)).toList();
        }
        posts = postRepository.findAll();
        return posts.stream()
                .map(post -> modelMapperService.forResponse().map(post, GetPostResponse.class)).toList();
    }

    @Override
    public GetPostResponse getPostById(int id) {
        Post post = postRepository.findById(id).orElse(null);
        // throw new exception
        return modelMapperService.forResponse().map(post, GetPostResponse.class);
    }

    @Override
    public Post createPost(CreatePostRequest createPostRequest) {
        userBusinessRules.checkIfUserNotExists(createPostRequest.getUserId());

        Post post = modelMapperService.forRequest().map(createPostRequest, Post.class);

        return postRepository.save(post);
    }

    @Override
    public Post updatePost(UpdatePostRequest updatePostRequest) {
        postBusinessRules.checkIfPostNotExists(updatePostRequest.getId());

        Post post = postRepository.findById(updatePostRequest.getId()).orElse(null);
        post.setTitle(updatePostRequest.getTitle());
        post.setText(updatePostRequest.getText());
        return postRepository.save(post);
    }

    @Override
    public void deletePostById(int id) {
        postBusinessRules.checkIfPostNotExists(id);

        postRepository.deleteById(id);
    }
}
