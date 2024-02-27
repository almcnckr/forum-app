package com.project.forlite.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CreateCommentRequest {
    private int userId;
    private int postId;
    private String text;
}
