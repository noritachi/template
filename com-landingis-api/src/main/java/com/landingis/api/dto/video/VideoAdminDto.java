package com.landingis.api.dto.video;

import com.landingis.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class VideoAdminDto extends ABasicAdminDto {
    private Long id;
    private String title;
    private String description;
    private String url;
    private String avatar;
    private Integer kind;
}
