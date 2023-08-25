package com.skewwhiffy.auraltester.dto.question;

interface QuestionResponseElement {
    companion object {
        fun text(text: String) = TextQuestionResponseElement(text)
        fun abc(abc: String) = AbcQuestionResponseElement(abc)
    }
    /*
    static QuestionResponseElement text(String text) {
        return new TextQuestionResponseElement(text);
    }

    static QuestionResponseElement abc(String abc) {
        return new AbcQuestionResponseElement(abc);
    }
     */
}
