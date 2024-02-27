package com.project.forlite.controllers;

import com.project.forlite.business.abstracts.CommentService;
import com.project.forlite.business.requests.CreateCommentRequest;
import com.project.forlite.business.requests.UpdateCommentRequest;
import com.project.forlite.business.responses.GetCommentResponse;
import com.project.forlite.entities.Comment;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/comments")
public class CommentRestController {
    private CommentService commentService;

    @GetMapping
    public List<GetCommentResponse> getAllComments(@RequestParam Optional<Integer> userId, @RequestParam Optional<Integer> postId){
        return commentService.getAllComments(userId, postId);
    }

    @GetMapping("/{commentId}")
    public GetCommentResponse getCommentById(@PathVariable int commentId){
        return commentService.getCommentById(commentId);
    }

    @PostMapping
    public Comment createComment(@RequestBody CreateCommentRequest createCommentRequest){
        return commentService.createComment(createCommentRequest);
    }

    @PutMapping
    public Comment updateComment(@RequestBody UpdateCommentRequest updateCommentRequest){
        return commentService.updateComment(updateCommentRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteComment(@PathVariable int commentId){
        commentService.deleteCommentById(commentId);
    }
}
