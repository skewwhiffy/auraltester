package com.skewwhiffy.auraltester.model

import com.fasterxml.jackson.databind.ObjectMapper
import com.skewwhiffy.auraltester.dto.question.QuestionType
import kotlin.reflect.KClass

abstract class QuestionFactory<TDao : Any>(private val objectMapper: ObjectMapper) {
    fun getQuestion(json: String): Question<TDao> {
        val dao = objectMapper.readValue(json, dao.java)
        return getQuestion(dao)
    }

    abstract fun makeNewQuestion(): Question<TDao>

    abstract val questionType: QuestionType

    abstract val dao: KClass<TDao>

    abstract fun getQuestion(dao: TDao): Question<TDao>
}
