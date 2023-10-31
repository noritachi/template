package com.landingis.api.form.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateCourseForm {

    @NotEmpty(message = "courseName cannot be null")
    @ApiModelProperty(required = true)
    private String courseName;


    @NotNull(message = "courseKind cannot be null")
    @ApiModelProperty(required = true)
    private Integer courseKind;

    @ApiModelProperty(name = "parentId")
    private Long parentId;
}
