package com.skewwhiffy.auraltester.notation.model.abc

import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote

data class ClefSpaceNotes(val clef: Clef) : ClefNotes {
    override val notes: List<AbsoluteNote>
        get() = (1..3)
            .fold(listOf(clef.lowLedgerNote.upOne.skipOne)) { soFar, _ -> soFar + soFar[soFar.size - 1].skipOne }
            .map { it.withNoteName }
}
