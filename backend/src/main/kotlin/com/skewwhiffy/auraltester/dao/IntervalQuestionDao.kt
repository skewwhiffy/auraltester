package com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.annotation.Dao
import com.skewwhiffy.auraltester.controller.ClefType

@Dao
class IntervalQuestionDao(
    val clef: ClefType,
    val lowerNote: AbsoluteNoteDao,
    val upperNote: AbsoluteNoteDao
)