package com.skewwhiffy.auraltester.controller

import com.ninjasquad.springmockk.MockkBean
import com.skewwhiffy.auraltester.dto.question.QuestionRequest
import com.skewwhiffy.auraltester.dto.question.QuestionResponse
import com.skewwhiffy.auraltester.dto.question.QuestionType
import com.skewwhiffy.auraltester.service.QuestionService
import io.mockk.every
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.UUID

@SpringBootTest
class QuestionControllerTest {
    @MockkBean
    private lateinit var questionService: QuestionService

    @Autowired
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
