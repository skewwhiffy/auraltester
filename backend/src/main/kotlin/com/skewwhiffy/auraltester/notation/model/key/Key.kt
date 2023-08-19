package com.skewwhiffy.auraltester.notation.model.key

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.Accidental
import com.skewwhiffy.auraltester.notation.model.note.Note

data class Key(val note: Note, val isMinor: Boolean) {
    companion object {
        val cMajor: Key
            get() = major(Note.c)

        fun major(note: Note): Key {
            return Key(note, false)
        }

        fun minor(note: Note): Key {
            return Key(note, true)
        }

    }

    val abc: String
        get() = note.displayString + (if (isMinor) "m" else "")

    /*
    public String getDisplayString() {
        return MessageFormat.format(
            "{0} {1}",
            note.getDisplayString(),
            isMinor ? "minor" : "major"
        );
    }

*/

    fun getAbc(note: AbsoluteNote): String = getAccidentalAbc(note.note) + getNoteAbc(note)
    /*

    public boolean canRenderSignature() {
        return isMinor
        ? getRelativeMajor().canRenderSignature()
        : "C G D A E B F# C# F Bb Eb Ab Db Gb Cb".contains(note.getDisplayString());
    }
    */

    fun getNoteAbc(note: AbsoluteNote): String {
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
    /*

public Key getRelativeMinor() {
    return isMinor ? this : new Key(note.downMajorSecond().downMajorSecond().sharpen(), true);
}

public Key getRelativeMajor() {
    return isMinor ? new Key(note.upMajorSecond().upMajorSecond().flatten(), false) : this;
}

public Key getRelative() {
    return isMinor ? getRelativeMajor() : getRelativeMinor();
}
*/

    val notes: List<Note>
        get() = (if (isMinor) listOf(2, 1, 2, 2, 1, 2) else listOf(2, 2, 1, 2, 2, 2))
            .fold(listOf(note)) { soFar, tones ->
                val lastNote = soFar.get(soFar.size - 1)
                val nextNote = if (tones == 2) lastNote.upMajorSecond else lastNote.upMajorSecond.flat
                return soFar + listOf(nextNote)
            }
}

