package com.skewwhiffy.notation.model.abc

import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.notation.model.clef.Clef

data class ClefSpaceNotes(private val clef: Clef) : ClefNotes {
  override val notes: List<AbsoluteNote> = (1..3)
    .fold(listOf(clef.lowLedgerNote.upOne.skipOne)) { soFar, _ -> soFar + soFar.last().skipOne }
    .map { it.withNoteName }
}