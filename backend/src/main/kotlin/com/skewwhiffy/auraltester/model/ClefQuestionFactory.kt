package com.skewwhiffy.auraltester.model

import com.fasterxml.jackson.databind.ObjectMapper
import com.skewwhiffy.auraltester.controller.ClefType
import com.skewwhiffy.auraltester.dao.ClefQuestionDao
import com.skewwhiffy.auraltester.dao.model
import com.skewwhiffy.auraltester.dto.question.QuestionType
import com.skewwhiffy.auraltester.helper.oneOf
import com.skewwhiffy.auraltester.notation.factory.ClefFactory
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.service.AbcService
import org.springframework.stereotype.Service
import kotlin.reflect.KClass

@Service
class ClefQuestionFactory(
    private val abcService: AbcService,
    private val clefFactory: ClefFactory,
    objectMapper: ObjectMapper
) : QuestionFactory<ClefQuestionDao>(objectMapper) {
    override val newQuestion: Question<ClefQuestionDao>
        get() {
            val clefType = oneOf(ClefType.entries)
            val clef = clefFactory.get(clefType)
            val noteCandidates = AbsoluteNote.range(clef.lowLedgerNote, clef.highLedgerNote)
            val note = oneOf(noteCandidates)
            return ClefQuestion(abcService, clefFactory.get(clefType), note)
        }
    override val questionType: QuestionType
        get() = QuestionType.CLEF

    override val dao: KClass<ClefQuestionDao>
        get() = ClefQuestionDao::class

    override fun getQuestion(dao: ClefQuestionDao): Question<ClefQuestionDao> {
        return ClefQuestion(abcService, clefFactory.get(dao.type), dao.absoluteNote.model)
    }
}
