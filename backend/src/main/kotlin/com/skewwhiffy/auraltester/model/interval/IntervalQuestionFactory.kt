package com.skewwhiffy.auraltester.com.skewwhiffy.auraltester.model.interval

import com.fasterxml.jackson.databind.ObjectMapper
import com.skewwhiffy.auraltester.com.skewwhiffy.auraltester.dao.IntervalQuestionDao
import com.skewwhiffy.auraltester.dto.question.QuestionType
import com.skewwhiffy.auraltester.model.Question
import com.skewwhiffy.auraltester.model.QuestionFactory
import kotlin.reflect.KClass

class IntervalQuestionFactory(objectMapper: ObjectMapper) : QuestionFactory<IntervalQuestionDao>(objectMapper) {
    override val newQuestion: Question<IntervalQuestionDao>
        get() = TODO("Not yet implemented")
    override val questionType: QuestionType
        get() = TODO("Not yet implemented")
    override val dao: KClass<IntervalQuestionDao>
        get() = TODO("Not yet implemented")

    override fun getQuestion(dao: IntervalQuestionDao): Question<IntervalQuestionDao> {
        return IntervalQuestion()
    }
}