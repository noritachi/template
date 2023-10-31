package com.landingis.api.dto.video;

import com.landingis.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class VideoDto extends ABasicAdminDto {

    private Long id;
    private String videoTitle;
    private String videoDescription;
    private String videoUrl;
    private String videoAvatar;

}
