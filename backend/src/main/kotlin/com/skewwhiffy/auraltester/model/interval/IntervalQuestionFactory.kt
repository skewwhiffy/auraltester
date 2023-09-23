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
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection
import com.skewwhiffy.auraltester.service.ScaleService
import org.springframework.stereotype.Service

@Service
class IntervalQuestionFactory(
    private val clefFactory: ClefFactory,
    private val keyFactory: KeyFactory,
    private val scaleService: ScaleService,
    private val scaleTypeFactory: ScaleTypeFactory,
    objectMapper: ObjectMapper
) : QuestionFactory<IntervalQuestionDao>(objectMapper) {
    private val keys = "C,G,D,A,E,B,F#,C#,F,Bb,Eb,Ab,Db,Gb,Cb"
        .split(",")
        .map(keyFactory::getKey)
        .map { it.relativeMinor }
    private val possibleScaleTypes = listOf(
        scaleTypeFactory.minorHarmonic,
        scaleTypeFactory.minorMelodic,
    )

    override fun makeNewQuestion(): Question<IntervalQuestionDao> {
        val clef = oneOf(ClefType.entries).let(clefFactory::get)
        return (1..2)
            .map { oneOf(scaleTypeFactory.minorHarmonic, scaleTypeFactory.minorMelodic) }
            .map { scaleService.getScale(clef, oneOf(keys).note, it, oneOf(ScaleDirection.entries)) }
            .map { oneOf(it.notes) }
            .let { IntervalQuestion(clef, it.min(), it.max()) }
    }

    override val questionType = QuestionType.INTERVAL

    override val dao = IntervalQuestionDao::class

    override fun getQuestion(dao: IntervalQuestionDao): Question<IntervalQuestionDao> {
        TODO("Not yet implemented")
    }
}