package com.skewwhiffy.auraltester.model.interval

import com.skewwhiffy.auraltester.dao.IntervalQuestionDao
import com.skewwhiffy.auraltester.dto.question.AnswerType
import com.skewwhiffy.auraltester.dto.question.QuestionResponseElement
import com.skewwhiffy.auraltester.model.Question
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.Accidental
import com.skewwhiffy.auraltester.notation.model.note.IntervalNotes
import com.skewwhiffy.auraltester.notation.model.note.Note
import com.skewwhiffy.auraltester.service.AbcService

class IntervalQuestion(
    private val abcService: AbcService,
    private val clef: Clef,
    private val lowerNote: AbsoluteNote,
    private val upperNote: AbsoluteNote
) : Question<IntervalQuestionDao>() {
    override val dao by lazy { IntervalQuestionDao(clef.clefType, lowerNote.dao, upperNote.dao) }

    override val questionElements by lazy {
        listOf(
            QuestionResponseElement.text("What is the interval?"),
            QuestionResponseElement.abc(abc)
        )
    }

    override val answerTypes by lazy { listOf(AnswerType.INTERVAL) }

    override val correctResponse by lazy {
        listOf(
            QuestionResponseElement.text("Well done, this interval is $interval"),
            QuestionResponseElement.abc(abc)
        )
    }

    override val incorrectResponse by lazy {
        val naturalIntervalLowerNote = AbsoluteNote(Note(lowerNote.note.noteName, Accidental.natural), lowerNote.octave)
        val naturalInterval = Interval(interval.degree, 0)
        val naturalIntervalAbc =
            abcService.getAbc(clef, IntervalNotes(naturalIntervalLowerNote, naturalInterval), Key.cMajor)
        val naturalIntervalResponse = if (naturalInterval == interval && naturalIntervalLowerNote == lowerNote)
            listOf()
        else listOf(
            QuestionResponseElement.abc(naturalIntervalAbc.abc),
            QuestionResponseElement.text("This interval is a ${naturalInterval.displayString}")
        )
        listOf(
            QuestionResponseElement.text("Sorry, the correct answer is ${interval.displayString}"),
        ) + naturalIntervalResponse + listOf(
            QuestionResponseElement.abc(abc),
            QuestionResponseElement.text("This interval is a ${interval.displayString}")
        )
    }

    override val answer by lazy { listOf(interval.toString()) }

    private val interval by lazy { (upperNote - lowerNote).interval }

    private val abc by lazy {
        abcService.getAbc(
            clef,
            IntervalNotes(lowerNote, (upperNote - lowerNote).interval),
            Key.cMajor
        ).abc
    }
}
