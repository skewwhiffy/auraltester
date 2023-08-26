package com.skewwhiffy.auraltester.model

import com.skewwhiffy.auraltester.dao.ClefQuestionDao
import com.skewwhiffy.auraltester.dto.question.*
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.service.AbcService

import java.util.Collections

class ClefQuestion(
    private val abcService: AbcService,
    private val clef: Clef,
    private val absoluteNote: AbsoluteNote
) : Question<ClefQuestionDao>() {
    override val dao by lazy { ClefQuestionDao(clef.clefType, absoluteNote.dao) }

    /*
    @Override
    public ClefQuestionDao toDao() {
        return new ClefQuestionDao (clef.clefType(), absoluteNote.toDao());
    }
    */

    override val questionElements by lazy {
        listOf(
            QuestionResponseElement.text("What is the name of this note"),
            QuestionResponseElement.abc(abc)
        )
    }

    override val answerTypes by lazy { listOf(AnswerType.NOTE_NAME) }

    override val correctResponse by lazy {
        listOf(
            QuestionResponseElement.text("Well done, this note is ${absoluteNote.note.noteName}"),
            QuestionResponseElement.abc(abc)
        )
    }

    override val incorrectResponse by lazy {
        listOf(
            QuestionResponseElement.text(
                "Sorry, the correct answer is ${absoluteNote.note.noteName}. See the sequence below which counts the question's note from middle C.",
            ),
            QuestionResponseElement.abc(sequenceAbc),
            QuestionResponseElement.text("Original question is below."),
            QuestionResponseElement.abc(abc)
        )
    }

    override val answer
        get() = listOf(absoluteNote.note.noteName)

    private val abc by lazy { abcService.getAbc(clef, absoluteNote).abc }

    private val sequenceAbc by lazy { abcService.getAbc(clef, noteSequence).abc }

    private val noteSequence by lazy { getNoteSequence(listOf()) }

    private fun getNoteSequence(soFar: List<AbsoluteNote>): List<AbsoluteNote> {
        if (soFar.isEmpty()) {
            return getNoteSequence(Collections.singletonList(AbsoluteNote.middleC.withNoteName))
        }
        val lastNoteSoFar = soFar[soFar.size - 1]
        if (lastNoteSoFar == absoluteNote.withNoteName) {
            return soFar
        }
        val nextNote = if (lastNoteSoFar < absoluteNote) lastNoteSoFar.upOne else lastNoteSoFar.downOne
        return getNoteSequence(soFar + nextNote.withNoteName)
    }
}
