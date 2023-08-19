package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote
import com.skewwhiffy.auraltester.notation.model.note.Accidental
import com.skewwhiffy.auraltester.notation.model.note.Note
import com.skewwhiffy.auraltester.notation.model.note.Octave
import org.springframework.stereotype.Service

import java.lang.IllegalArgumentException
import java.text.MessageFormat
import java.util.Locale

@Service
class NoteFactory {
    fun getAbsoluteNote(rawNote: String): AbsoluteNote {
        return AbsoluteNote(getNote(rawNote), getOctave(rawNote), null)
    }

    fun getNote(rawNote: String): Note {
        return Note(getNoteName(rawNote), getAccidental(rawNote))
    }

    fun getNoteName(rawNote: String): String {
        val noteLetter = getNoteLetter(rawNote).uppercase(Locale.UK)
        if (noteLetter.compareTo("A") < 0 || noteLetter.compareTo("G") > 0) {
            throw IllegalArgumentException(
                MessageFormat.format("'{0}' is not a valid note name", noteLetter)
            )
        }
        return noteLetter
    }

    private fun getAccidental(rawNote: String): Accidental {
        val getOffset: (Char) -> Int = {
            when (it) {
                'x' -> 2
                '#' -> 1
                'b' -> -1
                else -> throw IllegalArgumentException(
                    MessageFormat.format("Not valid accidental: '{0}'", it)
                )
            }
        }
        return getRawAccidental(rawNote).toList().map { getOffset(it) }.sum().let { Accidental(it) }
    }

    fun getOctave(rawNote: String): Octave {
        val getOffset: (Char) -> Int = {
            when (it) {
                '\'' -> 1
                ',' -> -1
                else -> throw IllegalArgumentException("Not valid octave indicator: '$it'")
            }
        }
        val offsetBase = getRawOctave(rawNote).toList().map { getOffset(it) }.sum()
        val offset = offsetBase + if (rawNote.lowercase().equals(rawNote)) 1 else 0
        return Octave(offset)
    }


    private fun getNoteLetter(rawNote: String): String {
        return rawNote.substring(0, 1)
    }

    private fun getRawAccidental(rawNote: String): String {
        return getRawAccidentalFromAccidentalMarks(rawNote.substring(1))
    }

    private fun getRawAccidentalFromAccidentalMarks(accidentalMarks: String): String {
        return if (accidentalMarks.endsWith("'") || accidentalMarks.endsWith(","))
            getRawAccidentalFromAccidentalMarks(accidentalMarks.substring(0, accidentalMarks.length - 1))
        else accidentalMarks
    }

    private fun getRawOctave(rawNote: String): String {
        return rawNote
            .substring(getNoteLetter(rawNote).length + getRawAccidental(rawNote).length)
    }
}
