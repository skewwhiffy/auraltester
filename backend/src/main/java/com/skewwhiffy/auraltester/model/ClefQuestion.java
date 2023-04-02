package com.skewwhiffy.auraltester.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import lombok.Builder;

@Builder
public record ClefQuestion(
        ClefType type,
        AbsoluteNote absoluteNote
) {
    @JsonIgnore
    public ClefAnswer getAnswer() {
        return ClefAnswer.builder().noteName(absoluteNote.note().noteName()).build();
    }
}
