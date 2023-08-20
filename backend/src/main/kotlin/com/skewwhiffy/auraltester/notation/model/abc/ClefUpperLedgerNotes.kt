package com.skewwhiffy.auraltester.notation.model.abc

import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote

data class ClefUpperLedgerNotes(val clef: Clef) : ClefNotes {
    override val notes: List<AbsoluteNote>
        get() = (1..5)
            .fold(listOf(clef.highLedgerNote.downOne)) { soFar, _ -> soFar + soFar[soFar.size - 1].upOne }
            .map { it.withNoteName }
}
