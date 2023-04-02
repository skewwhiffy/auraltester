package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.dto.question.QuestionRequest;
import com.skewwhiffy.auraltester.dto.question.QuestionResponse;
import com.skewwhiffy.auraltester.model.ClefType;
import com.skewwhiffy.auraltester.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
@AllArgsConstructor
public class QuestionService {
    private static final SecureRandom random = new SecureRandom();
    private final QuestionRepository questionRepository;

    public QuestionResponse get(QuestionRequest request) {
        return switch (request.type()) {
            case CLEF -> getClefQuestion();
        };
    }

    private QuestionResponse getClefQuestion() {
        val clefType = oneOf(ClefType.values());
        throw new IllegalArgumentException();
    }

    private static <T> T oneOf(T[] value) {
        return value[random.nextInt(value.length)];
    }
}
