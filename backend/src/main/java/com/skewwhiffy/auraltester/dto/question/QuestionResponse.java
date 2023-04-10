package com.skewwhiffy.auraltester.dto.question;

import lombok.Builder;
import lombok.val;

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
    @SuppressWarnings("unused")
    public static class QuestionResponseBuilder {
        public QuestionResponseBuilder element(QuestionResponseElement element) {
            List<QuestionResponseElement> originalElements = elements == null
                    ? Collections.emptyList()
                    : elements;
            return elements(Stream.concat(originalElements.stream(), Stream.of(element)).toList());
        }

        public QuestionResponseBuilder answerType(AnswerType answerType) {
            List<AnswerType> originalAnswerTypes = answerTypes == null
                    ? Collections.emptyList()
                    : answerTypes;
            return answerTypes(Stream.concat(originalAnswerTypes.stream(), Stream.of(answerType)).toList());
        }
    }
}
