package com.landingis.api.controller;

import com.landingis.api.constant.LandingISConstant;
import com.landingis.api.dto.ApiMessageDto;
import com.landingis.api.dto.ErrorCode;
import com.landingis.api.dto.ResponseListObj;
import com.landingis.api.dto.quiz.QuizAdminDto;
import com.landingis.api.exception.RequestException;
import com.landingis.api.form.quiz.CreateQuizForm;
import com.landingis.api.form.quiz.UpdateQuizForm;
import com.landingis.api.mapper.QuizMapper;
import com.landingis.api.service.LandingIsApiService;
import com.landingis.api.storage.criteria.QuizCriteria;
import com.landingis.api.storage.model.Account;
import com.landingis.api.storage.model.Quiz;
import com.landingis.api.storage.repository.AccountRepository;
import com.landingis.api.storage.repository.QuizRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/quiz")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Slf4j
public class QuizController extends ABasicController{

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    QuizMapper quizMapper;

    @Autowired
    LandingIsApiService landingIsApiService;

    @Autowired
    AccountRepository accountRepository;


//    @GetMapping(value = "/get_article/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ApiMessageDto<ArticleDto> listArticle(@PathVariable("id") Long id){
//        ApiMessageDto<ArticleDto> apiMessageDto = new ApiMessageDto<>();
//
//        Article articlePage = articleRepository.findById(id).orElse(null);
//        apiMessageDto.setData(articleMapper.fromEntityToDto(articlePage));
//        apiMessageDto.setMessage("List article success");
//        return apiMessageDto;
//    }

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<ResponseListObj<QuizAdminDto>> list(QuizCriteria quizCriteria, Pageable pageable) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.QUIZ_ERROR_UNAUTHORIZED);
        }
        ApiMessageDto<ResponseListObj<QuizAdminDto>> responseListObjApiMessageDto = new ApiMessageDto<>();

        Page<Quiz> list = quizRepository.findAll(quizCriteria.getSpecification(), pageable);
        ResponseListObj<QuizAdminDto> responseListObj = new ResponseListObj<>();
        responseListObj.setData(quizMapper.fromEntityListToQuizDtoListNoQuizAnswer(list.getContent()));
        responseListObj.setPage(pageable.getPageNumber());
        responseListObj.setTotalPage(list.getTotalPages());
        responseListObj.setTotalElements(list.getTotalElements());

        responseListObjApiMessageDto.setData(responseListObj);
        responseListObjApiMessageDto.setMessage("Get list success");
        return responseListObjApiMessageDto;
    }

    @GetMapping(value = "/get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<QuizAdminDto> get(@PathVariable("id") Long id){
        Account currentUser = accountRepository.findById(getCurrentUserId()) .orElse(null);
        if(currentUser == null
                || !currentUser.getKind().equals(LandingISConstant.USER_KIND_ADMIN)
                && !currentUser.getKind().equals(LandingISConstant.USER_KIND_EMPLOYEE)
                && !currentUser.getKind().equals(LandingISConstant.USER_KIND_COLLABORATOR)) {
            throw new RequestException(ErrorCode.QUIZ_ERROR_UNAUTHORIZED);
        }

        ApiMessageDto<QuizAdminDto> result = new ApiMessageDto<>();
        Quiz quiz = quizRepository.findById(id).orElse(null);
        if(quiz == null){
            throw new RequestException(ErrorCode.QUIZ_ERROR_NOT_FOUND);
        }
        if(!currentUser.getKind().equals(LandingISConstant.USER_KIND_ADMIN)
                && !quiz.getStatus().equals(LandingISConstant.STATUS_ACTIVE)) {
            throw new RequestException(ErrorCode.QUIZ_ERROR_NOT_FOUND);
        }

        result.setData(quizMapper.fromEntityToQuizDto(quiz));
        result.setMessage("Get quiz success");
        return result;
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> create(@Valid @RequestBody CreateQuizForm createQuizForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.QUIZ_ERROR_UNAUTHORIZED);
        }
        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();

        Quiz quiz = quizMapper.fromCreateQuizFormToEntity(createQuizForm);

        quizRepository.save(quiz);
        apiMessageDto.setMessage("Create quiz success");
        return apiMessageDto;
    }

    @PutMapping(value = "/update", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> update(@Valid @RequestBody UpdateQuizForm updateQuizForm, BindingResult bindingResult) {
        if(!isAdmin()){
            throw new RequestException(ErrorCode.QUIZ_ERROR_UNAUTHORIZED);
        }

        ApiMessageDto<String> apiMessageDto = new ApiMessageDto<>();
        Quiz quiz = quizRepository.findById(updateQuizForm.getId()).orElse(null);
        if (quiz == null) {
            throw new RequestException(ErrorCode.QUIZ_ERROR_NOT_FOUND);
        }

        quizMapper.fromUpdateQuizFormToEntity(updateQuizForm, quiz);

        quizRepository.save(quiz);

        apiMessageDto.setMessage("Update quiz success");
        return apiMessageDto;

    }

    @DeleteMapping(value = "/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiMessageDto<String> delete(@PathVariable("id") Long id){
        if(!isAdmin()){
            throw new RequestException(ErrorCode.ARTICLE_ERROR_UNAUTHORIZED);
        }
        ApiMessageDto<String> result = new ApiMessageDto<>();
        Quiz quiz = quizRepository.findById(id).orElse(null);
        if(quiz == null){
            throw new RequestException(ErrorCode.ARTICLE_ERROR_NOT_FOUND);
        }
        quizRepository.delete(quiz);
        result.setMessage("Delete success");
        return result;
    }

}