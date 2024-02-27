package com.project.forlite.business.requests;

import lombok.Data;

@Data
public class CreateCommentRequest {
    private int userId;
    private int postId;
    private String text;
}
