package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.dto.question.AnswerResponse
import com.skewwhiffy.auraltester.dto.question.QuestionRequest
import com.skewwhiffy.auraltester.dto.question.QuestionResponse
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class QuestionService(
    //private val questionFactories: Collection<QuestionFactory>,
    //private val questionRespository: QuestionRepository
) {/*
    private static final ObjectMapper objectMapper = new ObjectMapper();
    */

    fun get(request: QuestionRequest): QuestionResponse {
        val questionType = request.type
        throw RuntimeException()
        /*
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
        ))
        val question = factory.getNewQuestion()
        try {
            val questionJson = objectMapper.writeValueAsString(question.toDao())
            val questionEntity = new QuestionDao(questionType, questionJson)
            val savedQuestionEntity = questionRepository.save(questionEntity)
            return QuestionResponse
                .builder()
                .questionId(savedQuestionEntity.getId())
                .elements(question.getQuestionElements())
                .answerTypes(question.getAnswerTypes())
                .build()
        } catch (JsonProcessingException ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
            "Cannot serialize clef question"
            )
        }
         */
    }


    fun answer(id: UUID, answers: List<String>): AnswerResponse {/*
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
            */
        throw RuntimeException()
    }
}
