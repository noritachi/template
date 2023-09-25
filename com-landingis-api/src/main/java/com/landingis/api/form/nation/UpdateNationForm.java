package com.landingis.api.form.nation;

import com.landingis.api.validation.Status;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class UpdateNationForm {

    @NotNull(message = "id cannot be null")
    @ApiModelProperty(required = true)
    private Long id;

    @NotEmpty(message = "nationName cannot be null")
    @ApiModelProperty(required = true)
    private String nationName;

    @NotEmpty(message = "nationPostCode cannot be null")
    @ApiModelProperty(required = true)
    private String nationPostCode;

}
