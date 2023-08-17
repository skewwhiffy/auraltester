package com.skewwhiffy.auraltester.dto.question;

public interface QuestionResponseElement {
    static QuestionResponseElement text(String text) {
        return new TextQuestionResponseElement(text);
    }

    static QuestionResponseElement abc(String abc) {
        return new AbcQuestionResponseElement(abc);
    }

}
