package com.skewwhiffy.auraltester.dto.question;

import lombok.Builder;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Builder
public record AnswerResponse(
        List<QuestionResponseElement> elements,
        List<String> correctAnswer,
        boolean isCorrect
) {
}
