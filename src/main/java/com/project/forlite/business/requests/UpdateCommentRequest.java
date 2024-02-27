package com.project.forlite.business.requests;

import lombok.Data;

@Data
public class UpdateCommentRequest {
    private int id;
    private String text;
}
