package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.dao.QuestionDao;
import com.skewwhiffy.auraltester.dto.question.AnswerType;
import com.skewwhiffy.auraltester.dto.question.QuestionRequest;
import com.skewwhiffy.auraltester.dto.question.QuestionResponseElement;
import com.skewwhiffy.auraltester.dto.question.QuestionType;
import com.skewwhiffy.auraltester.model.Question;
import com.skewwhiffy.auraltester.model.QuestionFactory;
import com.skewwhiffy.auraltester.repository.QuestionRepository;
import com.skewwhiffy.auraltester.test.util.TestData;
import jakarta.persistence.Id;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {
    @Mock
    private QuestionRepository questionRepository;
    @InjectMocks
    private QuestionService questionService;
    @SuppressWarnings("rawtypes")
    @Spy
    private ArrayList<QuestionFactory> questionFactories;
    @SuppressWarnings("rawtypes")
    @Mock
    private Question question;
    private Object dao;

    @BeforeEach
    void setUp() {
        questionFactories.addAll(IntStream
                .range(0, 2)
                .mapToObj(it -> mock(QuestionFactory.class))
                .toList());
        dao = TestData.random().string();
    }

    @ParameterizedTest
    @EnumSource(QuestionType.class)
    void when_questionRequested_then_validQuestionReturned_and_questionSerialized(QuestionType questionType) {
        val request = new QuestionRequest(questionType);
        val questionFactory = questionFactories.get(0);
        List<QuestionResponseElement> questionElements = Collections.emptyList();
        List<AnswerType> answerTypes = Collections.emptyList();
        when(questionFactory.getQuestionType()).thenReturn(questionType);
        when(questionFactory.getNewQuestion()).thenReturn(question);
        when(question.getQuestionElements()).thenReturn(questionElements);
        when(question.toDao()).thenReturn(dao);
        val id = UUID.randomUUID();
        when(questionRepository.save(any(QuestionDao.class))).then(it -> {
            val original = (QuestionDao) it.getArgument(0);
            val idField = Arrays.stream(QuestionDao.class.getDeclaredFields())
                    .filter(field -> field.isAnnotationPresent(Id.class))
                    .findFirst()
                    .orElseThrow();
            idField.setAccessible(true);
            idField.set(original, id);
            return original;
        });

        val actual = questionService.get(request);

        assertThat(actual.questionId()).isEqualTo(id);
        assertThat(actual.elements()).isEqualTo(questionElements);
        assertThat(actual.answerTypes()).isEqualTo(answerTypes);
    }
}
