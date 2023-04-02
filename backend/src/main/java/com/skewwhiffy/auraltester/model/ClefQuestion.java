package com.skewwhiffy.auraltester.model;

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ClefQuestion {
    private final ClefType type;
    private final AbsoluteNote absoluteNote;
    private final AnswerType answerType = AnswerType.NOTE;

    public ClefAnswer getAnswer() {
        return ClefAnswer.builder().noteName(absoluteNote.note().noteName()).build();
    }
}
