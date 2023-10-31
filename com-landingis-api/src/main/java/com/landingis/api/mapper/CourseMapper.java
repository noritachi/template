package com.landingis.api.mapper;


import com.landingis.api.dto.course.CourseDto;
import com.landingis.api.form.course.CreateCourseForm;
import com.landingis.api.form.course.UpdateCourseForm;
import com.landingis.api.storage.model.Course;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface CourseMapper {

    @Mapping(source = "courseName", target = "name")
    @Mapping(source = "courseKind", target = "kind")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminCreateMapping")
    Course fromCreateCourseFormToEntity(CreateCourseForm createCourseForm);

    @Mapping(source = "courseName", target = "name")
    @Mapping(source = "courseKind", target = "kind")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminUpdateMapping")
    void fromUpdateCourseFormToEntity(UpdateCourseForm updateCourseForm, @MappingTarget Course course);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "courseName")
    @Mapping(source = "kind", target = "courseKind")
    @Mapping(source = "parentCourse.id", target = "parentId")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminGetMappingNews")
    CourseDto fromEntityToAdminDto(Course course);

    @IterableMapping(elementTargetType = CourseDto.class, qualifiedByName = "adminGetMappingNews")
    List<CourseDto> fromEntityListToCourseDtoList(List<Course> courses);


    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "courseName")
    @BeanMapping(ignoreByDefault = true)
    @Named("adminAutoCompleteMappingNews")
    CourseDto fromEntityToAdminDtoAutoComplete(Course course);

    @IterableMapping(elementTargetType = CourseDto.class, qualifiedByName = "adminAutoCompleteMappingNews")
    List<CourseDto> fromEntityListToCourseDtoAutoComplete(List<Course> courses);
}
