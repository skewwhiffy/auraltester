package com.skewwhiffy.auraltester.dao;

import com.skewwhiffy.auraltester.model.ClefType;

public record ClefQuestionDao(ClefType type, AbsoluteNoteDao absoluteNote) {
}
