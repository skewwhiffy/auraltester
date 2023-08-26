package com.skewwhiffy.auraltester.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.skewwhiffy.auraltester.dao.QuestionDao
import com.skewwhiffy.auraltester.dto.question.AnswerType
import com.skewwhiffy.auraltester.dto.question.QuestionRequest
import com.skewwhiffy.auraltester.dto.question.QuestionResponseElement
import com.skewwhiffy.auraltester.dto.question.QuestionType
import com.skewwhiffy.auraltester.model.Question
import com.skewwhiffy.auraltester.model.QuestionFactory
import com.skewwhiffy.auraltester.repository.QuestionRepository
import com.skewwhiffy.auraltester.test.util.TestData
import io.mockk.every
import io.mockk.mockk
import jakarta.persistence.Id
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import java.util.UUID

class QuestionServiceTest {
    private val objectMapper = ObjectMapper()
    private val questionRepository = mockk<QuestionRepository>()
    private val questionFactories = (1..2).map { mockk<QuestionFactory<*>>() }
    private val question = mockk<Question<*>>()
    private val dao = TestData.random.string

    private val questionService = QuestionService(questionFactories, questionRepository, objectMapper)

    @ParameterizedTest
    @EnumSource(QuestionType::class)
    fun when_questionRequested_then_validQuestionReturned_and_questionSerialized(questionType: QuestionType) {
        val request = QuestionRequest(questionType)
        val questionFactory = questionFactories[0]
        val questionElements: List<QuestionResponseElement> = emptyList()
        val answerTypes: List<AnswerType> = emptyList()
        every { questionFactory.questionType } returns questionType
        @Suppress("UNCHECKED_CAST")
        every { questionFactory.newQuestion } returns question as Question<out Any>
        every { question.questionElements } returns questionElements
        every { question.dao } returns dao
        val id = UUID.randomUUID()
        every { questionRepository.save(any(QuestionDao::class)) } answers { call ->
            val original = call.invocation.args[0] as QuestionDao
            val idField = QuestionDao::class.java.declaredFields.first { field -> field.annotations.any { it is Id } }
            idField.isAccessible = true
            idField.set(original, id)
            original
        }
        every { question.questionElements } returns listOf()
        every { question.answerTypes} returns listOf()

        val actual = questionService.get(request)

        assertThat(actual.questionId).isEqualTo(id)
        assertThat(actual.elements).isEqualTo(questionElements)
        assertThat(actual.answerTypes).isEqualTo(answerTypes)
    }
}
