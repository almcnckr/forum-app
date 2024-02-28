package com.project.forlite.business.responses;

import lombok.Data;

import java.util.List;

@Data
public class GetPostResponse {
    private int id;
    private int userId;
    private String userName;
    private String text;
    private String title;
}
