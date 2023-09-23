package com.landingis.api.mapper;

import com.landingis.api.dto.news.NewsAdminDto;
import com.landingis.api.dto.news.NewsDto;
import com.landingis.api.dto.news.NewsDtoListClient;
import com.landingis.api.form.news.CreateNewsForm;
import com.landingis.api.form.news.UpdateNewsForm;
import com.landingis.api.storage.model.News;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface NewsMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "newTitle")
    @Mapping(source = "content", target = "newContent")
    @Mapping(source = "description", target = "newDescription")
    @Mapping(source = "avatar", target = "newAvatar")
    @Mapping(source = "banner", target = "newBanner")
    NewsDto fromEntityToDto(News news);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "newTitle")
    @Mapping(source = "avatar", target = "newAvatar")
    @Mapping(source = "description", target = "newDescription")
    @Mapping(source = "banner", target = "newBanner")
    NewsDtoListClient fromEntityToDtoList(News news);
    @IterableMapping(elementTargetType = NewsDtoListClient.class)
    List<NewsDtoListClient> fromEntityListToDtoListClient(List<News> newsList);


    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "banner", target = "banner")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "pinTop", target = "pinTop")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminCreateMapping")
    News fromCreateNewsFormToEntity(CreateNewsForm createNewsForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "banner", target = "banner")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "pinTop", target = "pinTop")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminUpdateMapping")
    void fromUpdateNewsFormToEntity(UpdateNewsForm updateNewsForm, @MappingTarget News news);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "banner", target = "banner")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "pinTop", target = "pinTop")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminGetMappingNoNewsContent")
    NewsAdminDto fromEntityToNewsDtoNoNewsContent(News news);

    @IterableMapping(elementTargetType = NewsAdminDto.class, qualifiedByName = "adminGetMappingNoNewsContent")
    List<NewsAdminDto> fromEntityListToNewsDtoListNoNewsContent(List<News> news);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "banner", target = "banner")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "pinTop", target = "pinTop")
    @Mapping(source = "kind", target = "kind")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "createdDate", target = "createdDate")
    @Mapping(source = "modifiedBy", target = "modifiedBy")
    @Mapping(source = "createdBy", target = "createdBy")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminGetMapping")
    NewsAdminDto fromEntityToNewsDto(News news);
}
