package com.project.forlite.business.requests;

import lombok.Data;

@Data
public class CreatePostRequest {
    private int userId;
    private String title;
    private String text;
}
