package com.project.forlite.controllers;

import com.project.forlite.business.abstracts.PostService;
import com.project.forlite.business.requests.CreatePostRequest;
import com.project.forlite.business.requests.UpdatePostRequest;
import com.project.forlite.business.responses.GetPostResponse;
import com.project.forlite.entities.Post;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/posts")
public class PostRestController {
    private PostService postService;

    @GetMapping
    public List<GetPostResponse> getAllPosts(@RequestParam Optional<Integer> userId){
        return postService.getAllPosts(userId);
    }

    @GetMapping("/{postId}")
    public GetPostResponse getPostById(@PathVariable int postId){
        return postService.getPostById(postId);
    }

    @PostMapping
    public Post createPost(@RequestBody CreatePostRequest createPostRequest){
        return postService.createPost(createPostRequest);
    }

    @PutMapping
    public Post updatePost(@RequestBody UpdatePostRequest updatePostRequest){
        return postService.updatePost(updatePostRequest);
    }

    @DeleteMapping("/{postId}")
    public void deletePostById(@PathVariable int postId){
        postService.deletePostById(postId);
    }
}
