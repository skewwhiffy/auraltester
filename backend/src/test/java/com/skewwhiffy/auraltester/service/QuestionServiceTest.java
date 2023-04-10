package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.dao.Question;
import com.skewwhiffy.auraltester.dto.question.AbcQuestionResponseElement;
import com.skewwhiffy.auraltester.dto.question.QuestionRequest;
import com.skewwhiffy.auraltester.dto.question.QuestionType;
import com.skewwhiffy.auraltester.model.ClefType;
import com.skewwhiffy.auraltester.notation.factory.ClefFactory;
import com.skewwhiffy.auraltester.notation.model.abc.AbcProvider;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.repository.QuestionRepository;
import com.skewwhiffy.auraltester.test.util.TestData;
import jakarta.persistence.Id;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {
    @Mock
    private AbcService abcService;
    @Mock
    private ClefFactory clefFactory;
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionService questionService;
    @Captor
    private ArgumentCaptor<ClefType> clefTypeCaptor;
    private Clef clef;
    @Mock
    private AbcProvider abcProvider;
    private String abc;

    @BeforeEach
    void setUp() {
        clef = TestData.random().clef();
        lenient().when(clefFactory.get(clefTypeCaptor.capture())).thenReturn(clef);
        abc = TestData.random().string();
    }

    @Test
    void when_clefQuestionRequested_then_validClefQuestionReturned_and_questionSerialized() {
        val request = new QuestionRequest(QuestionType.CLEF);
        val id = UUID.randomUUID();
        when(questionRepository.save(any(Question.class))).then(it -> {
            val original = (Question) it.getArgument(0);
            val idField = Arrays.stream(Question.class.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Id.class))
                    .findFirst()
                    .orElseThrow();
            idField.setAccessible(true);
            idField.set(original, id);
            return original;
        });
        when(abcService.getAbc(eq(clef), any(AbsoluteNote.class))).thenReturn(abcProvider);
        when(abcProvider.getAbc()).thenReturn(abc);

        val actual = questionService.get(request);

        assertThat(actual.questionId()).isEqualTo(id);
        val abcElement = actual
                .elements()
                .stream()
                .filter(it -> it instanceof AbcQuestionResponseElement)
                .map(it -> (AbcQuestionResponseElement) it)
                .findFirst()
                .orElseThrow();
        assertThat(abcElement.abc()).isEqualTo(abc);
    }
}
