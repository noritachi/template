package com.landingis.api.mapper;

import com.landingis.api.dto.nation.NationDto;
import com.landingis.api.form.nation.CreateNationForm;
import com.landingis.api.form.nation.UpdateNationForm;
import com.landingis.api.storage.model.Nation;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-27T23:10:50+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.20 (Oracle Corporation)"
)
@Component
public class NationMapperImpl implements NationMapper {

    @Override
    public Nation fromCreateNationFormToEntity(CreateNationForm createNationForm) {
        if ( createNationForm == null ) {
            return null;
        }

        Nation nation = new Nation();

        nation.setKind( createNationForm.getNationKind() );
        nation.setName( createNationForm.getNationName() );
        nation.setPostCode( createNationForm.getNationPostCode() );

        return nation;
    }

    @Override
    public void fromUpdateNationFormToEntity(UpdateNationForm updateNationForm, Nation nation) {
        if ( updateNationForm == null ) {
            return;
        }

        if ( updateNationForm.getNationKind() != null ) {
            nation.setKind( updateNationForm.getNationKind().intValue() );
        }
        if ( updateNationForm.getNationName() != null ) {
            nation.setName( updateNationForm.getNationName() );
        }
        if ( updateNationForm.getNationPostCode() != null ) {
            nation.setPostCode( updateNationForm.getNationPostCode() );
        }
    }

    @Override
    public NationDto fromEntityToAdminDto(Nation nation) {
        if ( nation == null ) {
            return null;
        }

        NationDto nationDto = new NationDto();

        nationDto.setNationName( nation.getName() );
        nationDto.setNationKind( nation.getKind() );
        nationDto.setId( nation.getId() );
        nationDto.setNationPostCode( nation.getPostCode() );
        Long id = nationParentId( nation );
        if ( id != null ) {
            nationDto.setParentId( id.intValue() );
        }

        return nationDto;
    }

    @Override
    public List<NationDto> fromEntityListToNationDtoList(List<Nation> nations) {
        if ( nations == null ) {
            return null;
        }

        List<NationDto> list = new ArrayList<NationDto>( nations.size() );
        for ( Nation nation : nations ) {
            list.add( fromEntityToAdminDto( nation ) );
        }

        return list;
    }

    @Override
    public NationDto fromEntityToAdminDtoAutoComplete(Nation nation) {
        if ( nation == null ) {
            return null;
        }

        NationDto nationDto = new NationDto();

        nationDto.setNationName( nation.getName() );
        nationDto.setId( nation.getId() );
        nationDto.setNationPostCode( nation.getPostCode() );

        return nationDto;
    }

    @Override
    public List<NationDto> fromEntityListToNationDtoAutoComplete(List<Nation> nations) {
        if ( nations == null ) {
            return null;
        }

        List<NationDto> list = new ArrayList<NationDto>( nations.size() );
        for ( Nation nation : nations ) {
            list.add( fromEntityToAdminDtoAutoComplete( nation ) );
        }

        return list;
    }

    private Long nationParentId(Nation nation) {
        if ( nation == null ) {
            return null;
        }
        Nation parent = nation.getParent();
        if ( parent == null ) {
            return null;
        }
        Long id = parent.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
