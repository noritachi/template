package com.landingis.api.mapper;

import com.landingis.api.dto.video.VideoDto;
import com.landingis.api.dto.video.VideoAdminDto;
import com.landingis.api.form.video.CreateVideoForm;
import com.landingis.api.form.video.UpdateVideoForm;
import com.landingis.api.storage.model.Video;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface VideoMapper {

    @Mapping(source = "title", target = "title")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminCreateVideoMapping")
    Video fromCreateVideoFormToEntity(CreateVideoForm createVideoForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminUpdateVideoMapping")
    void fromUpdateVideoFormToEntity(UpdateVideoForm updateVideoForm, @MappingTarget Video video);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminGetMappingNoVideoContent")
    VideoAdminDto fromEntityToVideoDtoNoVideoContent(Video video);

    @IterableMapping(elementTargetType = VideoAdminDto.class, qualifiedByName = "adminGetMappingNoVideoContent")
    List<VideoAdminDto> fromEntityListToVideoDtoListNoVideoContent(List<Video> videos);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "url", target = "url")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminGetMapping")
    VideoAdminDto fromEntityToVideoDto(Video video);
}
