package com.landingis.api.mapper;

import com.landingis.api.dto.article.ArticleAdminDto;
import com.landingis.api.dto.article.ArticleDto;
import com.landingis.api.form.article.CreateArticleForm;
import com.landingis.api.form.article.UpdateArticleForm;
import com.landingis.api.storage.model.Article;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ArticleMapper {


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
    @Named("adminCreateArticleMapping")
    Article fromCreateArticleFormToEntity(CreateArticleForm createArticleForm);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "content", target = "content")
    @Mapping(source = "avatar", target = "avatar")
    @Mapping(source = "banner", target = "banner")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "pinTop", target = "pinTop")
    @Mapping(source = "status", target = "status")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminUpdateArticleMapping")
    void fromUpdateArticleFormToEntity(UpdateArticleForm updateArticleForm, @MappingTarget Article article);

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
    @Named("adminGetMappingNoArticleContent")
    ArticleAdminDto fromEntityToArticleDtoNoArticleContent(Article article);

    @IterableMapping(elementTargetType = ArticleAdminDto.class, qualifiedByName = "adminGetMappingNoArticleContent")
    List<ArticleAdminDto> fromEntityListToArticleDtoListNoArticleContent(List<Article> article);

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
    ArticleAdminDto fromEntityToArticleDto(Article article);
}
