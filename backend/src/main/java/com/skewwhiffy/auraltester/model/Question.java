package com.skewwhiffy.auraltester.model;

import com.skewwhiffy.auraltester.dto.question.AnswerResponse;
import com.skewwhiffy.auraltester.dto.question.AnswerType;
import com.skewwhiffy.auraltester.dto.question.QuestionResponseElement;
import lombok.val;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

public abstract class Question<TDao> {
    public abstract TDao toDao();

    public abstract List<QuestionResponseElement> getQuestionElements();

    public abstract List<AnswerType> getAnswerTypes();

    public abstract List<QuestionResponseElement> getCorrectResponse();

    public abstract List<QuestionResponseElement> getIncorrectResponse();

    public abstract List<String> getAnswer();

    public boolean isCorrect(List<String> answers) {
        val correctAnswers = getAnswer();
        if (correctAnswers == null || answers == null) {
            return false;
        }
        if (correctAnswers.size() != answers.size()) {
            return false;
        }
        if (correctAnswers.stream().anyMatch(Objects::isNull) || answers.stream().anyMatch(Objects::isNull)) {
            return false;
        }
        return IntStream
                .range(0, correctAnswers.size())
                .allMatch(it -> correctAnswers.get(it).equals(answers.get(it)));
    }

    public AnswerResponse answer(List<String> answers) {
        List<QuestionResponseElement> elements = isCorrect(answers) ? getCorrectResponse() : getIncorrectResponse();
        return AnswerResponse
                .builder()
                .elements(elements)
                .correctAnswer(getAnswer())
                .build();
    }

}
