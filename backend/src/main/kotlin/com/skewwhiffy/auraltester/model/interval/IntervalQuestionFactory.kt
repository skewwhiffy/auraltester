package com.skewwhiffy.auraltester.model.interval

import com.fasterxml.jackson.databind.ObjectMapper
import com.skewwhiffy.auraltester.dao.IntervalQuestionDao
import com.skewwhiffy.auraltester.controller.ClefType
import com.skewwhiffy.auraltester.dto.question.QuestionType
import com.skewwhiffy.auraltester.helper.oneOf
import com.skewwhiffy.auraltester.model.Question
import com.skewwhiffy.auraltester.model.QuestionFactory
import com.skewwhiffy.auraltester.notation.factory.ClefFactory
import com.skewwhiffy.auraltester.notation.factory.KeyFactory
import com.skewwhiffy.auraltester.notation.factory.ScaleTypeFactory
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.note.*
import com.skewwhiffy.auraltester.service.ScaleService
import org.springframework.stereotype.Service
import kotlin.math.absoluteValue

@Service
class IntervalQuestionFactory(
    private val clefFactory: ClefFactory,
    private val keyFactory: KeyFactory,
    private val scaleService: ScaleService,
    private val scaleTypeFactory: ScaleTypeFactory,
    objectMapper: ObjectMapper
) : QuestionFactory<IntervalQuestionDao>(objectMapper) {
    private val keys by lazy {
        "C,G,D,A,E,B,F#,C#,F,Bb,Eb,Ab,Db,Gb,Cb"
            .split(",")
            .map(keyFactory::getKey)
            .map { it.relativeMinor }
    }

    private val possibleScaleTypes by lazy {
        listOf(
            scaleTypeFactory.minorHarmonic,
            scaleTypeFactory.minorMelodic,
        )
    }

    override fun makeNewQuestion(): Question<IntervalQuestionDao> {
        val clef = oneOf(ClefType.entries).let(clefFactory::get)
        val intervalDegree = oneOf(1..8)
        val intervalDeviation = oneOf(
            when (intervalDegree) {
                in listOf(1, 4, 5, 8) -> -1..1
                else -> -2..1
            }
        )
        val interval = Interval(intervalDegree, intervalDeviation)
        val lowerNoteWithoutAccidental = AbsoluteNote.range(clef.lowLedgerNote, clef.highLedgerNote)
            .filter { it + interval < clef.highLedgerNote }
            .let { oneOf(it) }
        val lowerNote = listOf(
            Accidental.flat.flatten,
            Accidental.flat,
            Accidental.natural,
            Accidental.sharp,
            Accidental.sharp.sharpen
        )
            .map { Note(lowerNoteWithoutAccidental.note.noteName, it) }
            .map { AbsoluteNote(it, lowerNoteWithoutAccidental.octave) }
            .filter { (it + interval).note.accidental.offset.absoluteValue <= 2 }
            .let { oneOf(it) }

        return IntervalQuestion(clef, lowerNote, lowerNote + interval)
    }

    override val questionType = QuestionType.INTERVAL

    override val dao = IntervalQuestionDao::class

    override fun getQuestion(dao: IntervalQuestionDao): Question<IntervalQuestionDao> {
        TODO("Not yet implemented")
    }
}
