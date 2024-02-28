package com.project.forlite.business.abstracts;

import com.project.forlite.business.requests.CreateCommentRequest;
import com.project.forlite.business.requests.UpdateCommentRequest;
import com.project.forlite.business.responses.GetCommentResponse;
import com.project.forlite.entities.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<GetCommentResponse> getAllComments(Optional<Integer> userId, Optional<Integer> postId);

    GetCommentResponse getCommentById(int commentId);

    GetCommentResponse createComment(CreateCommentRequest createCommentRequest);

    GetCommentResponse updateComment(UpdateCommentRequest updateCommentRequest);

    void deleteCommentById(int commentId);
}
