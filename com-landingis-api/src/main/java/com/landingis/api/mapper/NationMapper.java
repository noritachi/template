package com.landingis.api.mapper;


import com.landingis.api.dto.nation.NationDto;
import com.landingis.api.form.nation.CreateNationForm;
import com.landingis.api.form.nation.UpdateNationForm;
import com.landingis.api.storage.model.Nation;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NationMapper {

    @Mapping(source = "nationName", target = "name")
    @Mapping(source = "nationPostCode", target = "postCode")
    @Mapping(source = "nationKind", target = "kind")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminCreateMapping")
    Nation fromCreateNationFormToEntity(CreateNationForm createNationForm);

    @Mapping(source = "nationName", target = "name")
    @Mapping(source = "nationPostCode", target = "postCode")
    @Mapping(source = "nationKind", target = "kind")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminUpdateMapping")
    void fromUpdateNationFormToEntity(UpdateNationForm updateNationForm, @MappingTarget Nation nation);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "nationName")
    @Mapping(source = "postCode", target = "nationPostCode")
    @Mapping(source = "kind", target = "nationKind")
    @Mapping(source = "parent.id", target = "parentId")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminGetMapping")
    NationDto fromEntityToAdminDto(Nation nation);

    @IterableMapping(elementTargetType = NationDto.class, qualifiedByName = "adminGetMapping")
    List<NationDto> fromEntityListToNationDtoList(List<Nation> nations);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "nationName")
    @Mapping(source = "postCode", target = "nationPostCode")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminAutoCompleteMapping")
    NationDto fromEntityToAdminDtoAutoComplete(Nation nation);

    @IterableMapping(elementTargetType = NationDto.class, qualifiedByName = "adminAutoCompleteMapping")
    List<NationDto> fromEntityListToNationDtoAutoComplete(List<Nation> nations);
}
