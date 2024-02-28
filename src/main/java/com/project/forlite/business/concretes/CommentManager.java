package com.project.forlite.business.concretes;

import com.project.forlite.business.abstracts.CommentService;
import com.project.forlite.business.abstracts.PostService;
import com.project.forlite.business.abstracts.UserService;
import com.project.forlite.business.requests.CreateCommentRequest;
import com.project.forlite.business.requests.UpdateCommentRequest;
import com.project.forlite.business.responses.GetCommentResponse;
import com.project.forlite.business.rules.CommentBusinessRules;
import com.project.forlite.business.rules.PostBusinessRules;
import com.project.forlite.business.rules.UserBusinessRules;
import com.project.forlite.core.utilities.mappers.ModelMapperService;
import com.project.forlite.dataAccess.CommentRepository;
import com.project.forlite.entities.Comment;
import com.project.forlite.entities.Post;
import com.project.forlite.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentManager implements CommentService {
    private CommentRepository commentRepository;
    private ModelMapperService modelMapperService;
    private UserBusinessRules userBusinessRules;
    private PostBusinessRules postBusinessRules;
    private CommentBusinessRules commentBusinessRules;

    @Override
    public List<GetCommentResponse> getAllComments(Optional<Integer> userId, Optional<Integer> postId) {
        List<Comment> comments;
        if (userId.isPresent() && postId.isPresent()) {
            userBusinessRules.checkIfUserNotExists(userId.get());
            postBusinessRules.checkIfPostNotExists(postId.get());
            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            userBusinessRules.checkIfUserNotExists(userId.get());
            comments = commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            postBusinessRules.checkIfPostNotExists(postId.get());
            comments = commentRepository.findByPostId(postId.get());
        } else {
            comments = commentRepository.findAll();
        }
        return comments.stream().map(comment -> modelMapperService.forResponse().map(comment, GetCommentResponse.class)).toList();
    }

    @Override
    public GetCommentResponse getCommentById(int commentId) {
        commentBusinessRules.checkIfCommentNotExists(commentId);

        Comment comment = commentRepository.findById(commentId).orElse(null);

        return modelMapperService.forResponse().map(comment, GetCommentResponse.class);
    }

    @Override
    public GetCommentResponse createComment(CreateCommentRequest createCommentRequest) {
        userBusinessRules.checkIfUserNotExists(createCommentRequest.getUserId());
        postBusinessRules.checkIfPostNotExists(createCommentRequest.getPostId());

        Comment comment = modelMapperService.forRequest().map(createCommentRequest, Comment.class);

        commentRepository.save(comment);

        return modelMapperService.forResponse().map(comment, GetCommentResponse.class);
    }

    @Override
    public GetCommentResponse updateComment(UpdateCommentRequest updateCommentRequest) {
        commentBusinessRules.checkIfCommentNotExists(updateCommentRequest.getId());

        Comment comment = modelMapperService.forRequest().map(updateCommentRequest, Comment.class);
        Comment tempComment = commentRepository.findById(updateCommentRequest.getId()).orElse(null);
        
        comment.setPost(tempComment.getPost());
        comment.setUser(tempComment.getUser());

        commentRepository.save(comment);

        return modelMapperService.forResponse().map(comment, GetCommentResponse.class);
    }

    @Override
    public void deleteCommentById(int commentId) {
        commentBusinessRules.checkIfCommentNotExists(commentId);

        commentRepository.deleteById(commentId);
    }
}