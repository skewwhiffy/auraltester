package com.skewwhiffy.notation.factory

import com.skewwhiffy.notation.model.AbsoluteNote
import com.skewwhiffy.notation.model.Accidental
import com.skewwhiffy.notation.model.Note
import com.skewwhiffy.notation.model.Octave
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

@Service
class NoteFactory {
  fun getAbsoluteNote(rawNote: String): AbsoluteNote {
    return AbsoluteNote(getNote(rawNote), getOctave(rawNote))
  }

  fun getNote(rawNote: String): Note {
    return Note(getNoteName(rawNote), getAccidental(rawNote))
  }

  private fun getNoteName(rawNote: String): String {
    return getNoteLetter(rawNote).uppercase().let {
      if (it < "A" || it > "G") throw IllegalArgumentException("'$it' is not a valid note name")
      it
    }
  }

  private fun getAccidental(rawNote: String): Accidental {
    fun getOffset(char: Char): Int {
      return when (char) {
        'x' -> 2
        '#' -> 1
        'b' -> -1
        else -> throw IllegalArgumentException("Not valid accidental: '$char'")
      }
    }
    return getRawAccidental(rawNote)
      .toCharArray()
      .map(::getOffset)
      .sum()
      .let(::Accidental)
  }

  private fun getOctave(rawNote: String): Octave {
    fun getOffset(char: Char): Int {
      return when (char) {
        '\'' -> 1
        ',' -> -1
        else -> throw IllegalArgumentException("Not valid octave indicator: '$char'")
      }
    }
    return getRawOctave(rawNote)
      .toCharArray()
      .map(::getOffset)
      .sum()
      .let { it + (if (rawNote.lowercase() == rawNote) 1 else 0) }
      .let(::Octave)
  }

  private fun getNoteLetter(rawNote: String): String {
    return rawNote.substring(0, 1)
  }

  private fun getRawAccidental(rawNote: String): String {
    fun getRawAccidental(str: String): String {
      return if (str.endsWith("'") || str.endsWith(",")) getRawAccidental(str.substring(0, str.length - 1))
      else str
    }

    return getRawAccidental(rawNote.substring(1))
  }

  private fun getRawOctave(rawNote: String): String {
    return rawNote
      .substring(getNoteLetter(rawNote).length + getRawAccidental(rawNote).length)
  }
}
