package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.dto.question.QuestionRequest
import com.skewwhiffy.auraltester.dto.question.QuestionResponse
import com.skewwhiffy.auraltester.dto.question.QuestionType
import com.skewwhiffy.auraltester.service.QuestionService
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)
class QuestionControllerTest {
    @MockK
    private lateinit var questionService: QuestionService

    @InjectMockKs
    private lateinit var questionController: QuestionController

    @Test
    fun when_getQuestion_then_proxiesToQuestionService() {
        val expected = QuestionResponse(UUID.randomUUID())
        val request = QuestionRequest(QuestionType.CLEF)
        every { questionService.get(request) } returns expected

        val actual = questionController.newQuestion(request)

        assertThat(actual).isEqualTo(expected)
    }
}
