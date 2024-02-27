package com.project.forlite.business.concretes;

import com.project.forlite.business.abstracts.CommentService;
import com.project.forlite.business.abstracts.PostService;
import com.project.forlite.business.abstracts.UserService;
import com.project.forlite.business.requests.CreateCommentRequest;
import com.project.forlite.business.requests.UpdateCommentRequest;
import com.project.forlite.business.responses.GetCommentResponse;
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
    private UserService userService;
    private PostService postService;
    private ModelMapperService modelMapperService;

    @Override
    public List<GetCommentResponse> getAllComments(Optional<Integer> userId, Optional<Integer> postId) {
        List<Comment> comments;
        if (userId.isPresent() && postId.isPresent()) {
            comments = commentRepository.findByUserIdAndPostId(userId.get(), postId.get());
        } else if (userId.isPresent()) {
            comments = commentRepository.findByUserId(userId.get());
        } else if (postId.isPresent()) {
            comments = commentRepository.findByPostId(postId.get());
        } else {
            comments = commentRepository.findAll();
        }
        return comments.stream().map(comment -> modelMapperService.forResponse().map(comment, GetCommentResponse.class)).toList();
    }

    @Override
    public GetCommentResponse getCommentById(int commentId) {
        //throw exception
        return modelMapperService.forResponse().map(commentRepository.findById(commentId).orElse(null), GetCommentResponse.class);
    }

    @Override
    public Comment createComment(CreateCommentRequest createCommentRequest) {
        User user = modelMapperService.forResponse().map(userService.getUserById(createCommentRequest.getUserId()), User.class);
        Post post = modelMapperService.forResponse().map(postService.getPostById(createCommentRequest.getPostId()), Post.class);
        if (user == null || post == null)
            return null;

        Comment comment = modelMapperService.forRequest().map(createCommentRequest, Comment.class);
        return commentRepository.save(comment);
    }

    @Override
    public Comment updateComment(UpdateCommentRequest updateCommentRequest) {
        Optional<Comment> comment = commentRepository.findById(updateCommentRequest.getId());
        if (comment.isPresent()) {
            Comment updatedComment = comment.get();
            updatedComment.setText(updateCommentRequest.getText());
            return commentRepository.save(updatedComment);
        }
        return null;
    }

    @Override
    public void deleteCommentById(int commentId) {
        commentRepository.deleteById(commentId);
    }
}