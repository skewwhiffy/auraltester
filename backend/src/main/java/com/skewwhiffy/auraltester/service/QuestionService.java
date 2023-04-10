package com.skewwhiffy.auraltester.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skewwhiffy.auraltester.dao.QuestionDao;
import com.skewwhiffy.auraltester.dto.question.AnswerResponse;
import com.skewwhiffy.auraltester.dto.question.QuestionRequest;
import com.skewwhiffy.auraltester.dto.question.QuestionResponse;
import com.skewwhiffy.auraltester.model.QuestionFactory;
import com.skewwhiffy.auraltester.notation.factory.ClefFactory;
import com.skewwhiffy.auraltester.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuestionService {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final AbcService abcService;
    private final ClefFactory clefFactory;
    private final Collection<QuestionFactory<?>> questionFactories;
    private final QuestionRepository questionRepository;

    public QuestionResponse get(QuestionRequest request) {
        val questionType = request.type();
        val factory = questionFactories
                .stream()
                .filter(it -> it.getQuestionType() == questionType)
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        MessageFormat.format(
                                "No question factory for {0}.",
                                questionType
                        )
                ));
        val question = factory.getNewQuestion();
        try {
            val questionJson = objectMapper.writeValueAsString(question.toDao());
            val questionEntity = new QuestionDao(questionType, questionJson);
            val savedQuestionEntity = questionRepository.save(questionEntity);
            return QuestionResponse
                    .builder()
                    .questionId(savedQuestionEntity.getId())
                    .elements(question.getQuestionElements())
                    .answerTypes(question.getAnswerTypes())
                    .build();
        } catch (JsonProcessingException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot serialize clef question"
            );
        }
    }

    public AnswerResponse answer(UUID id, List<String> answers) {
        val savedQuestionEntity = questionRepository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        MessageFormat.format(
                                "Question with ID '{0}' not found.",
                                id
                        )
                ));
        val factory = questionFactories
                .stream()
                .filter(it -> it.getQuestionType() == savedQuestionEntity.getQuestionType())
                .findAny()
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        MessageFormat.format(
                                "No question factory for {0}.",
                                savedQuestionEntity.getQuestionType()
                        )
                ));
        return factory
                .getQuestion(savedQuestionEntity.getQuestion())
                .answer(answers);
    }

}
