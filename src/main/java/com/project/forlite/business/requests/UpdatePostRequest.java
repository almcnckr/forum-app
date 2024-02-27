package com.project.forlite.business.requests;

import lombok.Data;

@Data
public class UpdatePostRequest {
    private int id;
    private String title;
    private String text;
}
