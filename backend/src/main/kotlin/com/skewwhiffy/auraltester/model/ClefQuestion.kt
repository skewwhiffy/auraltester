package com.skewwhiffy.auraltester.model;

import com.skewwhiffy.auraltester.dao.ClefQuestionDao;
import com.skewwhiffy.auraltester.dto.question.*;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.service.AbcService;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Stream;

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

    /*
    @Override
    public List < QuestionResponseElement > getCorrectResponse () {
        return Arrays.asList(
            QuestionResponseElement.text(
                MessageFormat.format(
                    "Well done, this note is {0}.",
                    absoluteNote.note().noteName()
                )
            ),
            QuestionResponseElement.abc(getAbc())
        );
    }

    @Override
    public List < QuestionResponseElement > getIncorrectResponse () {
        return Arrays.asList(
            QuestionResponseElement.text(
                MessageFormat.format(
                    "Sorry, the correct answer is {0}. See the sequence below which counts the question's note from middle C.",
                    absoluteNote.note().noteName()
                )
            ),
            QuestionResponseElement.abc(getSequenceAbc()),
            QuestionResponseElement.text("Original question is below."),
            QuestionResponseElement.abc(getAbc())
        );
    }
    */

    override val answer
        get() = listOf(absoluteNote.note.noteName)

    private val abc by lazy { abcService.getAbc(clef, absoluteNote).abc }

    /*
    private String getSequenceAbc() {
        return abcService.getAbc(clef, getNoteSequence()).getAbc();
    }

    private List < AbsoluteNote > getNoteSequence () {
        return getNoteSequence(Collections.emptyList());
    }

    private List < AbsoluteNote > getNoteSequence (List<AbsoluteNote> soFar) {
        if (soFar.isEmpty()) {
            return getNoteSequence(Collections.singletonList(AbsoluteNote.getMiddleC().withNoteName()));
        }
        val lastNoteSoFar = soFar.get(soFar.size() - 1);
        if (lastNoteSoFar.equals(absoluteNote.withNoteName())) {
            return soFar;
        }
        val nextNote = lastNoteSoFar.compareTo(absoluteNote) < 0
        ? lastNoteSoFar.upOne()
        : lastNoteSoFar.downOne();
        return getNoteSequence(Stream.concat(soFar.stream(), Stream.of(nextNote.withNoteName())).toList());
    }
     */
}
