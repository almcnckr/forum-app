package com.project.forlite.business.requests;

import lombok.Data;

@Data
public class CreateLikeRequest {
    private int userId;
    private int postId;
}
