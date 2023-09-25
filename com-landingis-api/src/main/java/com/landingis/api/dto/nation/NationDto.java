package com.landingis.api.dto.nation;

import com.landingis.api.dto.ABasicAdminDto;
import lombok.Data;

@Data
public class NationDto extends ABasicAdminDto {
    private String nationName;
    private String nationPostCode;
    private Integer nationKind;
    private Integer parentId;
}
