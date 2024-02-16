package com.skewwhiffy.auraltester.dto.question;

interface QuestionResponseElement {
    companion object {
        fun text(text: String) = TextQuestionResponseElement(text)
        fun abc(abc: String) = AbcQuestionResponseElement(abc)
    }
}
