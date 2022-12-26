package com.skewwhiffy.notation.model.abc

import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.notation.model.clef.Clef

data class ClefLowerLedgerNotes(private val clef: Clef) : ClefNotes {
  override val notes: List<AbsoluteNote> = (1..5)
    .fold(listOf(clef.lowLedgerNote.upOne)) { soFar, _ -> soFar + soFar.last().downOne }
    .map { it.withNoteName }
}