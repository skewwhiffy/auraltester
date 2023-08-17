package com.skewwhiffy.auraltester.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skewwhiffy.auraltester.dto.question.QuestionType;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;

public abstract class QuestionFactory<TDao> {
    private final static ObjectMapper objectMapper = new ObjectMapper();

    public Question<TDao> getQuestion(String json) {
        try {
            val dao = objectMapper.readValue(json, getDao());
            return getQuestion(dao);
        } catch (JsonProcessingException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    MessageFormat.format(
                            "Could not deserialize '{0}' into a ClefQuestion.",
                            json
                    )
            );
        }
    }

    public abstract Question<TDao> getNewQuestion();

    public abstract QuestionType getQuestionType();

    abstract Class<TDao> getDao();

    abstract Question<TDao> getQuestion(TDao dao);
}
