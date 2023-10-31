package com.landingis.api.dto.article;

import com.landingis.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class ArticleDto extends ABasicAdminDto {

    private Long id;
    private String articleTitle;
    private String articleDescription;
    private String articleContent;
    private String articleAvatar;
    private Integer articleKind;
    private String articleBanner;
}
