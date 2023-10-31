package com.landingis.api.form.course;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateCourseForm {

    @NotNull(message = "id cannot be null")
    @ApiModelProperty(required = true)
    private Long id;

    @NotEmpty(message = "courseName cannot be null")
    @ApiModelProperty(required = true)
    private String courseName;

    @NotNull(message = "courseKind cannot be null")
    @ApiModelProperty(required = true)
    private Long courseKind;

}
