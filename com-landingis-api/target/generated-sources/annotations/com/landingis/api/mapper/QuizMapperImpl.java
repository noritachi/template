package com.landingis.api.mapper;

import com.landingis.api.dto.quiz.QuizAdminDto;
import com.landingis.api.form.quiz.CreateQuizForm;
import com.landingis.api.form.quiz.UpdateQuizForm;
import com.landingis.api.storage.model.Quiz;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-31T20:34:36+0700",
    comments = "version: 1.3.1.Final, compiler: javac, environment: Java 11.0.20 (Oracle Corporation)"
)
@Component
public class QuizMapperImpl implements QuizMapper {

    @Override
    public Quiz fromCreateQuizFormToEntity(CreateQuizForm createQuizForm) {
        if ( createQuizForm == null ) {
            return null;
        }

        Quiz quiz = new Quiz();

        quiz.setQuestion( createQuizForm.getQuestion() );
        quiz.setKind( createQuizForm.getKind() );
        quiz.setAnswer( createQuizForm.getAnswer() );
        quiz.setOption3( createQuizForm.getOption3() );
        quiz.setOption4( createQuizForm.getOption4() );
        quiz.setOption1( createQuizForm.getOption1() );
        quiz.setOption2( createQuizForm.getOption2() );
        quiz.setStatus( createQuizForm.getStatus() );

        return quiz;
    }

    @Override
    public void fromUpdateQuizFormToEntity(UpdateQuizForm updateQuizForm, Quiz quiz) {
        if ( updateQuizForm == null ) {
            return;
        }

        if ( updateQuizForm.getQuestion() != null ) {
            quiz.setQuestion( updateQuizForm.getQuestion() );
        }
        if ( updateQuizForm.getAnswer() != null ) {
            quiz.setAnswer( updateQuizForm.getAnswer() );
        }
        if ( updateQuizForm.getOption3() != null ) {
            quiz.setOption3( updateQuizForm.getOption3() );
        }
        if ( updateQuizForm.getOption4() != null ) {
            quiz.setOption4( updateQuizForm.getOption4() );
        }
        if ( updateQuizForm.getOption1() != null ) {
            quiz.setOption1( updateQuizForm.getOption1() );
        }
        if ( updateQuizForm.getId() != null ) {
            quiz.setId( updateQuizForm.getId() );
        }
        if ( updateQuizForm.getOption2() != null ) {
            quiz.setOption2( updateQuizForm.getOption2() );
        }
        if ( updateQuizForm.getStatus() != null ) {
            quiz.setStatus( updateQuizForm.getStatus() );
        }
    }

    @Override
    public QuizAdminDto fromEntityToQuizDtoNoQuizAnswer(Quiz quiz) {
        if ( quiz == null ) {
            return null;
        }

        QuizAdminDto quizAdminDto = new QuizAdminDto();

        quizAdminDto.setQuestion( quiz.getQuestion() );
        quizAdminDto.setKind( quiz.getKind() );
        quizAdminDto.setCreatedDate( quiz.getCreatedDate() );
        quizAdminDto.setCreatedBy( quiz.getCreatedBy() );
        quizAdminDto.setOption3( quiz.getOption3() );
        quizAdminDto.setModifiedDate( quiz.getModifiedDate() );
        quizAdminDto.setOption4( quiz.getOption4() );
        quizAdminDto.setOption1( quiz.getOption1() );
        quizAdminDto.setModifiedBy( quiz.getModifiedBy() );
        quizAdminDto.setId( quiz.getId() );
        quizAdminDto.setOption2( quiz.getOption2() );
        quizAdminDto.setStatus( quiz.getStatus() );

        return quizAdminDto;
    }

    @Override
    public List<QuizAdminDto> fromEntityListToQuizDtoListNoQuizAnswer(List<Quiz> quiz) {
        if ( quiz == null ) {
            return null;
        }

        List<QuizAdminDto> list = new ArrayList<QuizAdminDto>( quiz.size() );
        for ( Quiz quiz1 : quiz ) {
            list.add( fromEntityToQuizDtoNoQuizAnswer( quiz1 ) );
        }

        return list;
    }

    @Override
    public QuizAdminDto fromEntityToQuizDto(Quiz quiz) {
        if ( quiz == null ) {
            return null;
        }

        QuizAdminDto quizAdminDto = new QuizAdminDto();

        quizAdminDto.setQuestion( quiz.getQuestion() );
        quizAdminDto.setKind( quiz.getKind() );
        quizAdminDto.setCreatedDate( quiz.getCreatedDate() );
        quizAdminDto.setAnswer( quiz.getAnswer() );
        quizAdminDto.setCreatedBy( quiz.getCreatedBy() );
        quizAdminDto.setOption3( quiz.getOption3() );
        quizAdminDto.setModifiedDate( quiz.getModifiedDate() );
        quizAdminDto.setOption4( quiz.getOption4() );
        quizAdminDto.setOption1( quiz.getOption1() );
        quizAdminDto.setModifiedBy( quiz.getModifiedBy() );
        quizAdminDto.setId( quiz.getId() );
        quizAdminDto.setOption2( quiz.getOption2() );
        quizAdminDto.setStatus( quiz.getStatus() );

        return quizAdminDto;
    }
}
