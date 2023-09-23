package com.landingis.api.dto.news;

import lombok.Data;

@Data
public class NewsDtoListClient {
    private Long id;
    private String newTitle;
    private String newDescription;
    private String newAvatar;
    private Integer newKind;
    private String newBanner;
}
