package com.project.forlite.business.abstracts;

import com.project.forlite.business.requests.CreatePostRequest;
import com.project.forlite.business.requests.UpdatePostRequest;
import com.project.forlite.business.responses.GetPostResponse;
import com.project.forlite.entities.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {
    List<GetPostResponse> getAllPosts(Optional<Integer> userId);

    GetPostResponse getPostById(int id);

    Post createPost(CreatePostRequest createPostRequest);

    Post updatePost(UpdatePostRequest updatePostRequest);

    void deletePostById(int id);
}
