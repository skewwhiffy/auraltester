package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.dto.question.QuestionRequest
import com.skewwhiffy.auraltester.dto.question.QuestionResponse
import com.skewwhiffy.auraltester.dto.question.QuestionType
import com.skewwhiffy.auraltester.service.QuestionService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension
import java.util.*

@ExtendWith(MockitoExtension::class)
class QuestionControllerTest {
    @Mock
    private lateinit var questionService: QuestionService

    @InjectMocks
    private lateinit var questionController: QuestionController

    @Test
    fun when_getQuestion_then_proxiesToQuestionService() {
        val expected = QuestionResponse(UUID.randomUUID())
        val request = QuestionRequest(QuestionType.CLEF)
        `when`(questionService.get(request)).thenReturn(expected)

        val actual = questionController.newQuestion(request)

        assertThat(actual).isEqualTo(expected)
    }
}
