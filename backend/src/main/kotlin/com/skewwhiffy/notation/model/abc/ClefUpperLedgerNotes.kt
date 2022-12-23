package com.skewwhiffy.notation.model.abc

import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.notation.model.clef.Clef

data class ClefUpperLedgerNotes(private val clef: Clef) : ClefNotes {
  override val notes: List<AbsoluteNote> = (1..5)
    .fold(listOf(clef.highLedgerNote.downOne)) { soFar, _ -> soFar + soFar.last().upOne }
    .map { it.withNoteName }
}