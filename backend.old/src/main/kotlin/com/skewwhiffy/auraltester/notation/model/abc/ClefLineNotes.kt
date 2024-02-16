package com.skewwhiffy.auraltester.notation.model.abc

import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote

data class ClefLineNotes(val clef: Clef) : ClefNotes {
    override val notes: List<AbsoluteNote>
        get() {
            return (1..4)
                .fold(listOf(clef.lowLedgerNote.skipOne)) { soFar, _ -> soFar + soFar[soFar.size - 1].skipOne }
                .map { it.withNoteName }
        }
}
