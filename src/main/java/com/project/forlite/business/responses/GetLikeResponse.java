package com.project.forlite.business.responses;

import lombok.Data;

@Data
public class GetLikeResponse {
    private int id;
    private int postId;
    private int userId;
}
