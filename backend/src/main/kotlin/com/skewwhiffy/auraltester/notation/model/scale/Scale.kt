package com.skewwhiffy.auraltester.notation.model.scale

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.NoteSequence
import com.skewwhiffy.auraltester.notation.model.note.displayString

data class Scale(val lowestNote: AbsoluteNote, val scaleType: ScaleType, val direction: ScaleDirection) : NoteSequence {
    val displayName
        get() = "${lowestNote.note.displayString} ${scaleType.displayName}"

    override val notes: List<AbsoluteNote>
        get() {
            val notes = scaleType
                .getIntervals(direction)
                .map(lowestNote::apply)
            return when (direction) {
                ScaleDirection.ASCENDING -> notes
                ScaleDirection.DESCENDING -> notes.reversed()
            }
        }
}
