package com.landingis.api.mapper;

import com.landingis.api.dto.news.NewsAdminDto;
import com.landingis.api.dto.news.NewsDto;
import com.landingis.api.dto.news.NewsDtoListClient;
import com.landingis.api.form.news.CreateNewsForm;
import com.landingis.api.form.news.UpdateNewsForm;
import com.landingis.api.storage.model.Category;
import com.landingis.api.storage.model.News;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-09-13T18:02:08+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.20 (Oracle Corporation)"
)
@Component
public class NewsMapperImpl implements NewsMapper {

    @Override
    public NewsDto fromEntityToDto(News news) {
        if ( news == null ) {
            return null;
        }

        NewsDto newsDto = new NewsDto();

        newsDto.setNewTitle( news.getTitle() );
        newsDto.setNewAvatar( news.getAvatar() );
        newsDto.setNewBanner( news.getBanner() );
        newsDto.setNewContent( news.getContent() );
        newsDto.setId( news.getId() );
        newsDto.setNewDescription( news.getDescription() );

        return newsDto;
    }

    @Override
    public NewsDtoListClient fromEntityToDtoList(News news) {
        if ( news == null ) {
            return null;
        }

        NewsDtoListClient newsDtoListClient = new NewsDtoListClient();

        newsDtoListClient.setNewTitle( news.getTitle() );
        newsDtoListClient.setNewAvatar( news.getAvatar() );
        newsDtoListClient.setNewBanner( news.getBanner() );
        newsDtoListClient.setId( news.getId() );
        newsDtoListClient.setNewDescription( news.getDescription() );

        return newsDtoListClient;
    }

    @Override
    public List<NewsDtoListClient> fromEntityListToDtoListClient(List<News> newsList) {
        if ( newsList == null ) {
            return null;
        }

        List<NewsDtoListClient> list = new ArrayList<NewsDtoListClient>( newsList.size() );
        for ( News news : newsList ) {
            list.add( fromEntityToDtoList( news ) );
        }

        return list;
    }

    @Override
    public News fromCreateNewsFormToEntity(CreateNewsForm createNewsForm) {
        if ( createNewsForm == null ) {
            return null;
        }

        News news = new News();

        news.setCategory( createNewsFormToCategory( createNewsForm ) );
        news.setKind( createNewsForm.getKind() );
        news.setBanner( createNewsForm.getBanner() );
        news.setDescription( createNewsForm.getDescription() );
        news.setAvatar( createNewsForm.getAvatar() );
        news.setTitle( createNewsForm.getTitle() );
        news.setContent( createNewsForm.getContent() );
        news.setPinTop( createNewsForm.getPinTop() );
        news.setStatus( createNewsForm.getStatus() );

        return news;
    }

    @Override
    public void fromUpdateNewsFormToEntity(UpdateNewsForm updateNewsForm, News news) {
        if ( updateNewsForm == null ) {
            return;
        }

        if ( updateNewsForm.getBanner() != null ) {
            news.setBanner( updateNewsForm.getBanner() );
        }
        if ( updateNewsForm.getDescription() != null ) {
            news.setDescription( updateNewsForm.getDescription() );
        }
        if ( updateNewsForm.getAvatar() != null ) {
            news.setAvatar( updateNewsForm.getAvatar() );
        }
        if ( updateNewsForm.getTitle() != null ) {
            news.setTitle( updateNewsForm.getTitle() );
        }
        if ( updateNewsForm.getContent() != null ) {
            news.setContent( updateNewsForm.getContent() );
        }
        if ( updateNewsForm.getPinTop() != null ) {
            news.setPinTop( updateNewsForm.getPinTop() );
        }
        if ( updateNewsForm.getId() != null ) {
            news.setId( updateNewsForm.getId() );
        }
        if ( updateNewsForm.getStatus() != null ) {
            news.setStatus( updateNewsForm.getStatus() );
        }
    }

    @Override
    public NewsAdminDto fromEntityToNewsDtoNoNewsContent(News news) {
        if ( news == null ) {
            return null;
        }

        NewsAdminDto newsAdminDto = new NewsAdminDto();

        newsAdminDto.setKind( news.getKind() );
        newsAdminDto.setBanner( news.getBanner() );
        newsAdminDto.setDescription( news.getDescription() );
        newsAdminDto.setAvatar( news.getAvatar() );
        newsAdminDto.setTitle( news.getTitle() );
        newsAdminDto.setCreatedDate( news.getCreatedDate() );
        newsAdminDto.setCreatedBy( news.getCreatedBy() );
        newsAdminDto.setModifiedDate( news.getModifiedDate() );
        newsAdminDto.setPinTop( news.getPinTop() );
        newsAdminDto.setModifiedBy( news.getModifiedBy() );
        newsAdminDto.setId( news.getId() );
        newsAdminDto.setCategoryId( newsCategoryId( news ) );
        newsAdminDto.setStatus( news.getStatus() );

        return newsAdminDto;
    }

    @Override
    public List<NewsAdminDto> fromEntityListToNewsDtoListNoNewsContent(List<News> news) {
        if ( news == null ) {
            return null;
        }

        List<NewsAdminDto> list = new ArrayList<NewsAdminDto>( news.size() );
        for ( News news1 : news ) {
            list.add( fromEntityToNewsDtoNoNewsContent( news1 ) );
        }

        return list;
    }

    @Override
    public NewsAdminDto fromEntityToNewsDto(News news) {
        if ( news == null ) {
            return null;
        }

        NewsAdminDto newsAdminDto = new NewsAdminDto();

        newsAdminDto.setKind( news.getKind() );
        newsAdminDto.setBanner( news.getBanner() );
        newsAdminDto.setDescription( news.getDescription() );
        newsAdminDto.setAvatar( news.getAvatar() );
        newsAdminDto.setTitle( news.getTitle() );
        newsAdminDto.setContent( news.getContent() );
        newsAdminDto.setCreatedDate( news.getCreatedDate() );
        newsAdminDto.setCreatedBy( news.getCreatedBy() );
        newsAdminDto.setModifiedDate( news.getModifiedDate() );
        newsAdminDto.setPinTop( news.getPinTop() );
        newsAdminDto.setModifiedBy( news.getModifiedBy() );
        newsAdminDto.setId( news.getId() );
        newsAdminDto.setCategoryId( newsCategoryId( news ) );
        newsAdminDto.setStatus( news.getStatus() );

        return newsAdminDto;
    }

    protected Category createNewsFormToCategory(CreateNewsForm createNewsForm) {
        if ( createNewsForm == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( createNewsForm.getCategoryId() );

        return category;
    }

    private Long newsCategoryId(News news) {
        if ( news == null ) {
            return null;
        }
        Category category = news.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
