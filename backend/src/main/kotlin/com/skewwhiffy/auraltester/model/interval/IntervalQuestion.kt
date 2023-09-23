package com.skewwhiffy.auraltester.model.interval

import com.skewwhiffy.auraltester.dao.IntervalQuestionDao
import com.skewwhiffy.auraltester.dto.question.AnswerType
import com.skewwhiffy.auraltester.dto.question.QuestionResponseElement
import com.skewwhiffy.auraltester.model.Question
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote

class IntervalQuestion(
    private val clef: Clef,
    private val lowerNote: AbsoluteNote,
    private val upperNote: AbsoluteNote
) : Question<IntervalQuestionDao>() {
    override val dao
        get() = TODO("Not yet implemented")
    override val questionElements: List<QuestionResponseElement>
        get() = TODO("Not yet implemented")
    override val answerTypes: List<AnswerType>
        get() = TODO("Not yet implemented")
    override val correctResponse: List<QuestionResponseElement>
        get() = TODO("Not yet implemented")
    override val incorrectResponse: List<QuestionResponseElement>
        get() = TODO("Not yet implemented")
    override val answer: List<String>
        get() = TODO("Not yet implemented")
}