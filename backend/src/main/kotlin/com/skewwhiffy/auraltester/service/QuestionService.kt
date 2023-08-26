package com.skewwhiffy.auraltester.service

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.skewwhiffy.auraltester.dao.QuestionDao
import com.skewwhiffy.auraltester.dto.question.AnswerResponse
import com.skewwhiffy.auraltester.dto.question.QuestionRequest
import com.skewwhiffy.auraltester.dto.question.QuestionResponse
import com.skewwhiffy.auraltester.model.QuestionFactory
import com.skewwhiffy.auraltester.repository.QuestionRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import java.util.UUID
import kotlin.jvm.optionals.getOrNull

@Service
class QuestionService(
    private val questionFactories: Collection<QuestionFactory<*>>,
    private val questionRepository: QuestionRepository,
    private val objectMapper: ObjectMapper
) {
    fun get(request: QuestionRequest): QuestionResponse {
        val questionType = request.type
        val factory = questionFactories.firstOrNull { it.questionType == questionType }
            ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "No question factory for $questionType")
        val question = factory.newQuestion
        try {
            val questionJson = objectMapper.writeValueAsString(question.dao)
            val questionEntity = QuestionDao(questionType, questionJson)
            val savedQuestionEntity = questionRepository.save(questionEntity)
            return QuestionResponse(savedQuestionEntity.id!!, question.questionElements, question.answerTypes)
        } catch (ex: JsonProcessingException) {
            throw ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Cannot serialize clef question"
            )
        }
    }


    fun answer(id: UUID, answers: List<String>): AnswerResponse {
        val savedQuestionEntity = questionRepository.findById(id).getOrNull() ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "Question with ID '$id' not found"
        )
        val factory = questionFactories.first { it.questionType == savedQuestionEntity.questionType }
        return factory
            .getQuestion(savedQuestionEntity.question)
            .answer(answers)
    }
}
