package com.skewwhiffy.auraltester.dto.question;

import java.util.*

data class QuestionResponse(
    val questionId: UUID,
    val elements: List<QuestionResponseElement> = listOf(),
    val answerTypes: List<AnswerType> = listOf()
)
