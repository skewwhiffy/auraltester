package com.skewwhiffy.auraltester.com.skewwhiffy.auraltester.model.clef

import com.fasterxml.jackson.databind.ObjectMapper
import com.skewwhiffy.auraltester.controller.ClefType
import com.skewwhiffy.auraltester.dao.ClefQuestionDao
import com.skewwhiffy.auraltester.dao.model
import com.skewwhiffy.auraltester.dto.question.QuestionType
import com.skewwhiffy.auraltester.helper.oneOf
import com.skewwhiffy.auraltester.model.Question
import com.skewwhiffy.auraltester.model.QuestionFactory
import com.skewwhiffy.auraltester.notation.factory.ClefFactory
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.service.AbcService
import org.springframework.stereotype.Service

@Service
class ClefQuestionFactory(
    private val abcService: AbcService,
    private val clefFactory: ClefFactory,
    objectMapper: ObjectMapper
) : QuestionFactory<ClefQuestionDao>(objectMapper) {
    override fun makeNewQuestion(): Question<ClefQuestionDao> {
        val clef = oneOf(ClefType.entries).let(clefFactory::get)
        val note = AbsoluteNote.range(clef.lowLedgerNote, clef.highLedgerNote).let(::oneOf)
        return ClefQuestion(abcService, clef, note)
    }

    override val questionType = QuestionType.CLEF

    override val dao = ClefQuestionDao::class

    override fun getQuestion(dao: ClefQuestionDao): Question<ClefQuestionDao> {
        return ClefQuestion(abcService, clefFactory.get(dao.type), dao.absoluteNote.model)
    }
}
