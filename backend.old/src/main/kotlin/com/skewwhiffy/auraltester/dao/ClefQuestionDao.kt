package com.skewwhiffy.auraltester.dao

import com.skewwhiffy.auraltester.annotation.Dao
import com.skewwhiffy.auraltester.controller.ClefType
import jakarta.persistence.Entity

@Dao
data class ClefQuestionDao(
    val type: ClefType,
    val absoluteNote: AbsoluteNoteDao
)
