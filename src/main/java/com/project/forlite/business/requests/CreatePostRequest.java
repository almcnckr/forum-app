package com.project.forlite.business.requests;

import lombok.Data;

@Data
public class CreatePostRequest {
    private String title;
    private String text;
    private int userId;
}
