package com.landingis.api.mapper;

import com.landingis.api.dto.course.CourseDto;
import com.landingis.api.form.course.CreateCourseForm;
import com.landingis.api.form.course.UpdateCourseForm;
import com.landingis.api.storage.model.Course;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-31T09:56:54+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.20 (Oracle Corporation)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Course fromCreateCourseFormToEntity(CreateCourseForm createCourseForm) {
        if ( createCourseForm == null ) {
            return null;
        }

        Course course = new Course();

        course.setKind( createCourseForm.getCourseKind() );
        course.setName( createCourseForm.getCourseName() );

        return course;
    }

    @Override
    public void fromUpdateCourseFormToEntity(UpdateCourseForm updateCourseForm, Course course) {
        if ( updateCourseForm == null ) {
            return;
        }

        if ( updateCourseForm.getCourseKind() != null ) {
            course.setKind( updateCourseForm.getCourseKind().intValue() );
        }
        if ( updateCourseForm.getCourseName() != null ) {
            course.setName( updateCourseForm.getCourseName() );
        }
    }

    @Override
    public CourseDto fromEntityToAdminDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDto courseDto = new CourseDto();

        courseDto.setCourseName( course.getName() );
        courseDto.setId( course.getId() );
        courseDto.setCourseKind( course.getKind() );
        Long id = courseParentCourseId( course );
        if ( id != null ) {
            courseDto.setParentId( id.intValue() );
        }

        return courseDto;
    }

    @Override
    public List<CourseDto> fromEntityListToCourseDtoList(List<Course> courses) {
        if ( courses == null ) {
            return null;
        }

        List<CourseDto> list = new ArrayList<CourseDto>( courses.size() );
        for ( Course course : courses ) {
            list.add( fromEntityToAdminDto( course ) );
        }

        return list;
    }

    @Override
    public CourseDto fromEntityToAdminDtoAutoComplete(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDto courseDto = new CourseDto();

        courseDto.setCourseName( course.getName() );
        courseDto.setId( course.getId() );

        return courseDto;
    }

    @Override
    public List<CourseDto> fromEntityListToCourseDtoAutoComplete(List<Course> courses) {
        if ( courses == null ) {
            return null;
        }

        List<CourseDto> list = new ArrayList<CourseDto>( courses.size() );
        for ( Course course : courses ) {
            list.add( fromEntityToAdminDtoAutoComplete( course ) );
        }

        return list;
    }

    private Long courseParentCourseId(Course course) {
        if ( course == null ) {
            return null;
        }
        Course parentCourse = course.getParentCourse();
        if ( parentCourse == null ) {
            return null;
        }
        Long id = parentCourse.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
