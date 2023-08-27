package com.skewwhiffy.auraltester.notation.model.abc

import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote

data class ClefLowerLedgerNotes(val clef: Clef) : ClefNotes {
    override val notes: List<AbsoluteNote>
        get() = (1..5)
            .fold(listOf(clef.lowLedgerNote.upOne)) { soFar, _ -> soFar + soFar[soFar.size - 1].downOne }
            .map { it.withNoteName }
}
