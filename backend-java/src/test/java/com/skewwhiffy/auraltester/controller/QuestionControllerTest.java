package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.question.QuestionRequest;
import com.skewwhiffy.auraltester.dto.question.QuestionResponse;
import com.skewwhiffy.auraltester.dto.question.QuestionType;
import com.skewwhiffy.auraltester.service.QuestionService;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionControllerTest {
    @Mock
    private QuestionService questionService;
    @InjectMocks
    private QuestionController questionController;

    @Test
    public void when_getQuestion_then_proxiesToQuestionService() {
        val expected = QuestionResponse
                .builder()
                .questionId(UUID.randomUUID())
                .build();
        val request = new QuestionRequest(QuestionType.CLEF);
        when(questionService.get(request)).thenReturn(expected);

        val actual = questionController.newQuestion(request);

        assertThat(actual).isEqualTo(expected);
    }
}
