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
    @SuppressWarnings("unused")
    public static class AnswerResponseBuilder {
        public AnswerResponseBuilder element(QuestionResponseElement element) {
            List<QuestionResponseElement> originalElements = elements == null
                    ? Collections.emptyList()
                    : elements;
            return elements(Stream.concat(originalElements.stream(), Stream.of(element)).toList());
        }

        public AnswerResponseBuilder correctAnswerElement(String correctAnswerElement) {
            List<String> originalCorrectAnswer = correctAnswer == null
                    ? Collections.emptyList()
                    : correctAnswer;
            return correctAnswer(Stream.concat(originalCorrectAnswer.stream(), Stream.of(correctAnswerElement)).toList());
        }
    }
}
