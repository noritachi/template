package com.landingis.api.controller;

import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.course.CourseDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.course.CreateCourseForm;
import com.landingis.api.form.course.UpdateCourseForm;
import com.landingis.api.mapper.CourseMapper;
import com.landingis.api.service.LandingIsApiService;
import com.landingis.api.storage.criteria.CourseCriteria;
import com.landingis.api.storage.model.Course;
import com.landingis.api.storage.repository.CourseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/course")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class CourseController extends ABasicController{

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CourseMapper courseMapper;

    @Autowired
    LandingIsApiService landingIsApiService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<CourseDto>> list(CourseCriteria courseCriteria, Pageable pageable) {
        if (!isAdmin()) {
            throw new RequestException(ErrorCode.COURSE_ERROR_UNAUTHORIZED, "Not allowed get list.");
        }
        ApiMessageDto<ResponseListObj<CourseDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Course> listCourse = courseRepository.findAll(courseCriteria.getSpecification(), pageable);
        ResponseListObj<CourseDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(courseMapper.fromEntityListToCourseDtoList(listCourse.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(listCourse.getTotalPages());
        responseListObj.setTotalElements(listCourse.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<CourseDto> get(@PathVariable("id") Long id) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.COURSE_ERROR_UNAUTHORIZED, "Not allowed get.");
        }
        ApiMessageDto<CourseDto> result = new ApiMessageDto<>();

        Course course = courseRepository.findById(id).orElse(null);

        if(course == null) {
            throw new RequestException(ErrorCode.COURSE_ERROR_NOT_FOUND, "Not found course.");
        }
        result.setData(courseMapper.fromEntityToAdminDto(course));
        result.setMessage("Get course success");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateCourseForm createCourseForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.COURSE_ERROR_UNAUTHORIZED, "Not allowed to create.");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        Course course = courseMapper.fromCreateCourseFormToEntity(createCourseForm);
        if(createCourseForm.getParentId() != null) {
            Course parentCourse = courseRepository.findById(createCourseForm.getParentId()).orElse(null);
            if(parentCourse == null || parentCourse.getParentCourse() != null) {
                throw new RequestException(ErrorCode.COURSE_ERROR_NOT_FOUND, "Not found course parent");
            }
            course.setParentCourse(parentCourse);
        }
        courseRepository.save(course);
        apiMessageDto.setMessage("Create course success");
        return apiMessageDto;

    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateCourseForm updateCourseForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.COURSE_ERROR_UNAUTHORIZED, "Not allowed to update.");
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Course course = courseRepository.findById(updateCourseForm.getId()).orElse(null);
        if(course == null) {
            throw new RequestException(ErrorCode.COURSE_ERROR_NOT_FOUND, "Not found course.");
        }
        courseMapper.fromUpdateCourseFormToEntity(updateCourseForm, course);
        courseRepository.save(course);
        apiMessageDto.setMessage("Update course success");
        return apiMessageDto;
    }

    @DeleteMapping(value = "/delete/{id}")
    public ApiMessageDto<CourseDto> delete(@PathVariable("id") Long id) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.COURSE_ERROR_NOT_FOUND, "Not allowed to delete.");
        }
        ApiMessageDto<CourseDto> result = new ApiMessageDto<>();

        Course course = courseRepository.findById(id).orElse(null);
        if(course == null) {
            throw new RequestException(ErrorCode.COURSE_ERROR_NOT_FOUND, "Not found course");
        }
        //landingIsApiService.deleteFile(course.getImage());
        courseRepository.delete(course);
        result.setMessage("Delete course success");
        return result;
    }
}


