package com.skewwhiffy.auraltester.model;

import com.skewwhiffy.auraltester.dao.ClefQuestionDao;
import com.skewwhiffy.auraltester.dto.question.*;
import com.skewwhiffy.auraltester.notation.factory.ClefFactory;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import com.skewwhiffy.auraltester.service.AbcService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.val;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

@Builder
@AllArgsConstructor
public final class ClefQuestion extends Question<ClefQuestionDao> {
    private final AbcService abcService;
    private final ClefFactory clefFactory;
    private final ClefType type;
    private final AbsoluteNote absoluteNote;

    @Override
    public ClefQuestionDao toDao() {
        return new ClefQuestionDao(type, absoluteNote.toDao());
    }

    @Override
    public List<QuestionResponseElement> getQuestionElements() {
        return Arrays.asList(
                QuestionResponseElement.text("What is the name of this note?"),
                QuestionResponseElement.abc(getAbc())
        );
    }

    @Override
    public List<AnswerType> getAnswerTypes() {
        return Collections.singletonList(AnswerType.NOTE_NAME);
    }

    @Override
    public List<QuestionResponseElement> getCorrectResponse() {
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
    public List<QuestionResponseElement> getIncorrectResponse() {
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

    @Override
    public List<String> getAnswer() {
        return Collections.singletonList(absoluteNote.note().noteName());
    }

    private String getAbc() {
        return abcService.getAbc(clefFactory.get(type), absoluteNote).getAbc();
    }

    private String getSequenceAbc() {
        return abcService.getAbc(clefFactory.get(type), getNoteSequence()).getAbc();
    }

    private List<AbsoluteNote> getNoteSequence() {
        return getNoteSequence(Collections.emptyList());
    }

    private List<AbsoluteNote> getNoteSequence(List<AbsoluteNote> soFar) {
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

}
