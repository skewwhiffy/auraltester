package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, Note, Octave}

import scala.annotation.tailrec

class NoteFactory(private val abc: String) {
  lazy val absoluteNote: AbsoluteNote = AbsoluteNote(note, octave)

  private lazy val note: Note = Note(noteName, accidental)

  private lazy val noteName: String = {
    rawNote.toUpperCase match
      case it if it >= "A" && it <= "G" => it
      case it => throw IllegalArgumentException(s"'$it' is not a valid note name'")
  }

  private lazy val accidental: Accidental = {
    val raw = rawAccidental
    val noteName = abc.substring(0, 1)
    val stripNote = abc.substring(1)
    if (noteName.toLowerCase == noteName) {
      Accidental.natural
    } else {
      Accidental.natural
    }
  }

  private val octave: Octave = {
    val noteName = abc.substring(0, 1)
    val stripNote = abc.substring(1)

    @tailrec
    def stripAccidental(toStrip: String): String =
      if toStrip.nonEmpty && "#xb".contains(toStrip.substring(0, 1)) then stripAccidental(toStrip.substring(1))
      else toStrip

    val accidentalStripped = stripAccidental(stripNote)
    noteName match
      case it if it.toLowerCase == it => Octave(accidentalStripped.length + 1)
      case _ => Octave(-accidentalStripped.length)
  }

  private lazy val rawNote: String = abc.substring(0, 1)

  private lazy val rawAccidental: String = {
    @tailrec
    def getRawAccidental(str: String): String = {
      if (str.endsWith("'")) then getRawAccidental(str.substring(0, str.length - 1))
      else str
    }
    getRawAccidental(abc.substring(1))
  }
}
