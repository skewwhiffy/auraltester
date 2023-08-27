package com.skewwhiffy.auraltester.notation.model.note;

interface NoteSequence {
    companion object {
        private data class NoteSequenceImpl(override val notes: List<AbsoluteNote>) : NoteSequence
        fun of(vararg notes: AbsoluteNote): NoteSequence {
            return NoteSequenceImpl(notes.toList())
        }
    }

    val notes: List<AbsoluteNote>
}
