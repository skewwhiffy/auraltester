package com.skewwhiffy.auraltester.service

import com.ninjasquad.springmockk.MockkBean
import com.skewwhiffy.auraltester.com.skewwhiffy.auraltester.model.clef.ClefQuestionFactory
import com.skewwhiffy.auraltester.com.skewwhiffy.auraltester.model.interval.IntervalQuestionFactory
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
import io.mockk.impl.annotations.MockK
import jakarta.persistence.Id
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import java.util.UUID
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.jvm.isAccessible

@SpringBootTest
@ActiveProfiles(profiles = ["test"])
class QuestionServiceTest {
    @MockkBean
    private lateinit var questionRepository: QuestionRepository

    @MockkBean
    private lateinit var clefQuestionFactory: ClefQuestionFactory

    @MockkBean
    private lateinit var intervalQuestionFactory: IntervalQuestionFactory

    @MockK
    private lateinit var question: Question<*>
    private val dao = TestData.random.string

    @Autowired
    private lateinit var questionService: QuestionService

    @BeforeEach
    fun setup() {
        every { clefQuestionFactory.questionType } returns QuestionType.CLEF
        every { intervalQuestionFactory.questionType } returns QuestionType.INTERVAL

        val questionFactories = this::class.members
            .filter { it.returnType.isSubtypeOf(QuestionFactory::class.starProjectedType) }
            .onEach { it.isAccessible = true }
            .map { it.call(this) as QuestionFactory<*> }
        @Suppress("UNCHECKED_CAST")
        questionFactories.forEach { questionFactory -> every { questionFactory.newQuestion } returns question as Question<out Any> }
    }

    @ParameterizedTest
    @EnumSource(QuestionType::class)
    fun when_questionRequested_then_validQuestionReturned_and_questionSerialized(questionType: QuestionType) {
        val request = QuestionRequest(questionType)
        val questionElements: List<QuestionResponseElement> = emptyList()
        val answerTypes: List<AnswerType> = emptyList()
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
        every { question.answerTypes } returns listOf()

        val actual = questionService.get(request)

        assertThat(actual.questionId).isEqualTo(id)
        assertThat(actual.elements).isEqualTo(questionElements)
        assertThat(actual.answerTypes).isEqualTo(answerTypes)
    }
}
