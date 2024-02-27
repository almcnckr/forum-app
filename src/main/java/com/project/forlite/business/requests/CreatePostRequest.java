package com.project.forlite.business.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CreatePostRequest {
    private String title;
    private String text;
    private int userId;
}
