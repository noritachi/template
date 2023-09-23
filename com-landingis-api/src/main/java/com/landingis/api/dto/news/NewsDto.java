package com.landingis.api.dto.news;

import lombok.Data;

@Data
public class NewsDto {
    private Long id;
    private String newTitle;
    private String newDescription;
    private String newContent;
    private String newAvatar;
    private Integer newKind;
    private String newBanner;
}
