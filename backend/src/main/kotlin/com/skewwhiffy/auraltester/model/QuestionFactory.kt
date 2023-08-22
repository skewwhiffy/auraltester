package com.skewwhiffy.auraltester.model

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.skewwhiffy.auraltester.dto.question.QuestionType
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException

import java.text.MessageFormat

abstract class QuestionFactory<TDao> {
    companion object {
        private val objectMapper: ObjectMapper by lazy(::ObjectMapper)
    }

    fun getQuestion(json: String): Question<TDao> {
        try {
            val dao = objectMapper.readValue(json, dao)
            return getQuestion(dao)
        } catch (e: JsonProcessingException) {
            throw ResponseStatusException(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "Could not deserialize '$json' into a ClefQuestion."
            )
        }
    }

    abstract val newQuestion: Question<TDao>

    abstract val questionType: QuestionType

    abstract val dao: Class<TDao>

    abstract fun getQuestion(dao: TDao): Question<TDao>
}
