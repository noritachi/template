package com.landingis.api.form.nation;

import com.landingis.api.validation.CategoryKind;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CreateNationForm {

    @NotEmpty(message = "nationName cannot be null")
    @ApiModelProperty(required = true)
    private String nationName;

    @NotEmpty(message = "nationPostCode cannot be null")
    @ApiModelProperty(required = true)
    private String nationPostCode;

    @NotNull(message = "nationKind cannot be null")
    @ApiModelProperty(required = true)
    private Integer nationKind;

    @ApiModelProperty(name = "parentId")
    private Long parentId;
}
