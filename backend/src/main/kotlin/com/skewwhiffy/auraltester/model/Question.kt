package com.skewwhiffy.auraltester.model

import com.skewwhiffy.auraltester.dto.question.AnswerType
import com.skewwhiffy.auraltester.dto.question.QuestionResponseElement

abstract class Question<TDao> {
    abstract val dao: TDao
    abstract val questionElements: List<QuestionResponseElement>
    abstract val answerTypes: List<AnswerType>
    /*
    public abstract List<QuestionResponseElement> getCorrectResponse();

    public abstract List<QuestionResponseElement> getIncorrectResponse();
    */

    abstract val answer: List<String>

    /*
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
        val isCorrect = isCorrect(answers);
        val elements = isCorrect ? getCorrectResponse() : getIncorrectResponse();
        return AnswerResponse
            .builder()
            .elements(elements)
            .correctAnswer(getAnswer())
            .isCorrect(isCorrect)
            .build();
    }
     */
}
