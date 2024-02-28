package com.project.forlite.controllers;

import com.project.forlite.business.abstracts.LikeService;
import com.project.forlite.business.requests.CreateLikeRequest;
import com.project.forlite.business.responses.GetLikeResponse;
import com.project.forlite.entities.Like;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/likes")
public class LikeRestController {
    private LikeService likeService;

    @GetMapping
    public List<GetLikeResponse> getAllLikes(@RequestParam Optional<Integer> userId, @RequestParam Optional<Integer> postId){
        return likeService.getAllLikes(userId, postId);
    }

    @GetMapping("/{likeId}")
    public GetLikeResponse getLikeById(@PathVariable int likeId){
        return likeService.getLikeById(likeId);
    }

    @PostMapping
    public GetLikeResponse createLike(@RequestBody CreateLikeRequest createLikeRequest){
        return likeService.createLike(createLikeRequest);
    }

    @DeleteMapping("/{likeId}")
    public void deleteLikeById(@PathVariable int likeId){
        likeService.deleteLikeById(likeId);
    }
}
