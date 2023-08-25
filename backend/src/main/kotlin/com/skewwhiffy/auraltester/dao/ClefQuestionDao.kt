package com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.controller.ClefType

data class ClefQuestionDao(
    val type: ClefType,
    val absoluteNote: AbsoluteNoteDao
)

