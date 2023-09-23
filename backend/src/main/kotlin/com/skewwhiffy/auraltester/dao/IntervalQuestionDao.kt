package com.skewwhiffy.auraltester.com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.annotation.Dao
import com.skewwhiffy.auraltester.controller.ClefType
import com.skewwhiffy.auraltester.dao.AbsoluteNoteDao

@Dao
class IntervalQuestionDao(
    val clef: ClefType,
    val lowerNote: AbsoluteNoteDao,
    val upperNote: AbsoluteNoteDao
)