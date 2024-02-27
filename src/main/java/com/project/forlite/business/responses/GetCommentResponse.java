package com.project.forlite.business.responses;

import lombok.Data;

@Data
public class GetCommentResponse {
    private int id;
    private int postId;
    private int userId;
    private String userName;
    private String text;
}
