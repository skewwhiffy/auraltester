package com.skewwhiffy.auraltester.dto.question;

import lombok.Builder;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Builder
public record QuestionResponse(
        UUID questionId,
        List<QuestionResponseElement> elements,
        List<AnswerType> answerTypes
) {
}
