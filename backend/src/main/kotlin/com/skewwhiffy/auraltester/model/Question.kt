package com.skewwhiffy.auraltester.model

import com.skewwhiffy.auraltester.dto.question.AnswerResponse
import com.skewwhiffy.auraltester.dto.question.AnswerType
import com.skewwhiffy.auraltester.dto.question.QuestionResponseElement

abstract class Question<TDao> {
    abstract val dao: TDao
    abstract val questionElements: List<QuestionResponseElement>
    abstract val answerTypes: List<AnswerType>
    abstract val correctResponse: List<QuestionResponseElement>
    abstract val incorrectResponse: List<QuestionResponseElement>

    abstract val answer: List<String>

    fun answer(answers: List<String>): AnswerResponse {
        val isCorrect = isCorrect(answers)
        val elements = if (isCorrect) correctResponse else incorrectResponse
        return AnswerResponse(elements, answer, isCorrect)
    }

    private fun isCorrect(answers: List<String>): Boolean {
        val correctAnswers = answer
        return answers.size == correctAnswers.size && answers.zip(correctAnswers).all { it.first == it.second}
    }
}
