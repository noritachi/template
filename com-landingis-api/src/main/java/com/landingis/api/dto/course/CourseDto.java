package com.landingis.api.dto.course;

import com.landingis.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class CourseDto extends ABasicAdminDto {
    private String courseName;
    private Integer courseKind;
    private Integer parentId;
}
