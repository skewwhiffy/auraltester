package com.skewwhiffy.auraltester.notation.model.key

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.Accidental
import com.skewwhiffy.auraltester.notation.model.note.Note
import com.skewwhiffy.auraltester.notation.model.note.abc
import com.skewwhiffy.auraltester.notation.model.note.displayString
import com.skewwhiffy.auraltester.notation.model.note.downMajorSecond
import com.skewwhiffy.auraltester.notation.model.note.flatten
import com.skewwhiffy.auraltester.notation.model.note.sharpen
import com.skewwhiffy.auraltester.notation.model.note.upMajorSecond

data class Key(val note: Note, val isMinor: Boolean) {
    companion object {
        val cMajor
            get() = major(Note.c)

        fun major(note: Note) = Key(note, false)

        fun minor(note: Note) = Key(note, true)

    }

    val abc
        get() = note.displayString + (if (isMinor) "m" else "")

    val displayString
        get() = "${note.displayString} ${if (isMinor) "minor" else "major"}"

    fun getAbc(note: AbsoluteNote) = getAccidentalAbc(note.note) + getNoteAbc(note)

    val canRenderSignature: Boolean by lazy { if (isMinor) relativeMajor.canRenderSignature else  "C G D A E B F# C# F Bb Eb Ab Db Gb Cb".contains(note.displayString) }

    private fun getNoteAbc(note: AbsoluteNote): String {
        val offset = note.octave.offsetFromDefault
        if (offset > 0) {
            return note.note.noteName.lowercase() + "'".repeat(offset - 1)
        }
        if (offset < 0) {
            return note.note.noteName + ",".repeat(-offset)
        }
        return note.note.noteName
    }

    private fun getAccidentalAbc(note: Note): String {
        val noteWithNoteName = notes
            .find { it.noteName == note.noteName }
            ?: throw IllegalArgumentException("No note ${note.noteName} in scale")
        return when (note.accidental) {
            noteWithNoteName.accidental -> ""
            Accidental.natural -> "="
            else -> note.accidental.abc
        }
    }

    val relativeMinor by lazy { if (isMinor) this else Key(note.downMajorSecond.downMajorSecond.sharpen, true) }

    val relativeMajor by lazy { if (isMinor) Key(note.upMajorSecond.upMajorSecond.flatten, false) else this }

    val relative
        get() = if (isMinor) relativeMajor else relativeMinor

    val notes: List<Note>
        get() = (if (isMinor) listOf(2, 1, 2, 2, 1, 2) else listOf(2, 2, 1, 2, 2, 2))
            .fold(listOf(note)) { soFar, tones ->
                val lastNote = soFar[soFar.size - 1]
                val nextNote = if (tones == 2) lastNote.upMajorSecond else lastNote.upMajorSecond.flatten
                return@fold soFar + listOf(nextNote)
            }
}

