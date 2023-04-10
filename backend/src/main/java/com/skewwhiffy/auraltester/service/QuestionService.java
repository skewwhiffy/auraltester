package com.skewwhiffy.auraltester.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skewwhiffy.auraltester.dao.Question;
import com.skewwhiffy.auraltester.dto.question.*;
import com.skewwhiffy.auraltester.helper.CollectionHelper;
import com.skewwhiffy.auraltester.model.ClefQuestion;
import com.skewwhiffy.auraltester.model.ClefType;
import com.skewwhiffy.auraltester.notation.factory.ClefFactory;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

@Service
@AllArgsConstructor
public class QuestionService {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final AbcService abcService;
    private final ClefFactory clefFactory;
    private final QuestionRepository questionRepository;

    public QuestionResponse get(QuestionRequest request) {
        return switch (request.type()) {
            case CLEF -> getClefQuestion();
        };
    }

    private QuestionResponse getClefQuestion() {
        val clefType = CollectionHelper.oneOf(Arrays.stream(ClefType.values()).toList());
        val clef = clefFactory.get(clefType);
        val noteCandidates = AbsoluteNote.range(clef.lowLedgerNote(), clef.highLedgerNote());
        val note = CollectionHelper.oneOf(noteCandidates);
        val question = ClefQuestion
                .builder()
                .absoluteNote(note)
                .type(clefType)
                .build();
        try {
            val questionJson = objectMapper.writeValueAsString(question);
            val questionEntity = new Question("clef", questionJson);
            val savedQuestionEntity = questionRepository.save(questionEntity);
            val abc = abcService.getAbc(clef, note).getAbc();
            return QuestionResponse
                    .builder()
                    .questionId(savedQuestionEntity.getId())
                    .element(new TextQuestionResponseElement("What is the name of this note?"))
                    .element(new AbcQuestionResponseElement(abc))
                    .answerType(AnswerType.NOTE_NAME)
                    .build();
        } catch (JsonProcessingException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Cannot serialize clef question"
            );
        }
    }
}
