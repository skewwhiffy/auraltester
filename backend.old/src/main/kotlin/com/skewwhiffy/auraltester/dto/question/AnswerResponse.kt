package com.skewwhiffy.auraltester.dto.question;

data class AnswerResponse(
    val elements: List<QuestionResponseElement>,
    val correctAnswer: List<String>,
    val isCorrect: Boolean
)
