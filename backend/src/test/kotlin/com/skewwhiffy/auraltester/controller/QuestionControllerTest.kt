package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.service.QuestionService
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension


@ExtendWith(MockitoExtension::class)
class QuestionControllerTest {
    @Mock
    private lateinit var questionService: QuestionService
    @InjectMocks
    private lateinit var questionController: QuestionController

    /*
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
     */
}
