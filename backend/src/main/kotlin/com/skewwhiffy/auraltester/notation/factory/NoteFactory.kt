package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.Accidental
import com.skewwhiffy.auraltester.notation.model.note.Note
import com.skewwhiffy.auraltester.notation.model.note.Octave
import org.springframework.stereotype.Service

import java.lang.IllegalArgumentException

@Service
class NoteFactory {
    fun getAbsoluteNote(rawNote: String) = AbsoluteNote(getNote(rawNote), getOctave(rawNote), null)

    fun getNote(rawNote: String) = Note(getNoteName(rawNote), getAccidental(rawNote))

    private fun getNoteName(rawNote: String) = getNoteLetter(rawNote)
        .uppercase()
        .apply { if (this < "A" || this > "G") throw throw IllegalArgumentException("'$this' is not a valid note name") }

    private fun getAccidental(rawNote: String): Accidental {
        val getOffset: (Char) -> Int = {
            when (it) {
                'x' -> 2
                '#' -> 1
                'b' -> -1
                else -> throw IllegalArgumentException("Not valid accidental: '$it'")
            }
        }
        return getRawAccidental(rawNote).sumOf(getOffset).let(::Accidental)
    }

    private fun getOctave(rawNote: String): Octave {
        val getOffset: (Char) -> Int = {
            when (it) {
                '\'' -> 1
                ',' -> -1
                else -> throw IllegalArgumentException("Not valid octave indicator: '$it'")
            }
        }
        val offsetBase = getRawOctave(rawNote).sumOf { getOffset(it) }
        val offset = offsetBase + if (rawNote.lowercase() == rawNote) 1 else 0
        return Octave(offset)
    }


    private fun getNoteLetter(rawNote: String) = rawNote.substring(0, 1)

    private fun getRawAccidental(rawNote: String) = rawNote
        .substring(1)
        .let(::getRawAccidentalFromAccidentalMarks)

    private fun getRawAccidentalFromAccidentalMarks(accidentalMarks: String): String =
        if (accidentalMarks.endsWith("'") || accidentalMarks.endsWith(","))
            getRawAccidentalFromAccidentalMarks(accidentalMarks.substring(0, accidentalMarks.length - 1))
        else accidentalMarks

    private fun getRawOctave(rawNote: String) = rawNote
        .substring(getNoteLetter(rawNote).length + getRawAccidental(rawNote).length)
}
