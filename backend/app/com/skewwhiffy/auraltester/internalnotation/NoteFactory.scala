package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, Note, Octave}

import scala.util.chaining.scalaUtilChainingOps
import scala.annotation.tailrec

class NoteFactory {
  def getAbsoluteNote(rawNote: String): AbsoluteNote =
    AbsoluteNote(getNote(rawNote), getOctave(rawNote))

  def getNote(rawNote: String): Note = Note(getNoteName(rawNote), getAccidental(rawNote))

  private def getNoteName(rawNote: String) = {
    getNoteLetter(rawNote).toUpperCase match {
      case it if it >= "A" && it <= "G" => it
      case it => throw new IllegalArgumentException(s"'$it' is not a valid note name'")
    }
  }

  private def getAccidental(rawNote: String) = getRawAccidental(rawNote)
    .toCharArray
    .map {
      case 'x' => 2
      case '#' => 1
      case 'b' => -1
      case it => throw new IllegalArgumentException(s"Not valid accidental: '$it'")
    }
    .sum
    .pipe(it => Accidental(it))

  private def getOctave(rawNote: String) = getRawOctave(rawNote)
    .toCharArray
    .map {
      case '\'' => 1
      case ',' => -1
      case it => throw new IllegalArgumentException(s"Not valid octave indicator: '$it'")
    }
    .sum
    .pipe(it => it + (if (rawNote.toLowerCase == rawNote) 1 else 0))
    .pipe(it => new Octave(it))

  private def getNoteLetter(rawNote: String) = rawNote.substring(0, 1)

  private def getRawAccidental(rawNote: String) = {
    @tailrec
    def getRawAccidental(str: String): String = {
      if (str.endsWith("'") || str.endsWith(",")) getRawAccidental(str.substring(0, str.length - 1))
      else str
    }

    getRawAccidental(rawNote.substring(1))
  }

  private def getRawOctave(rawNote: String) = rawNote
    .substring(getNoteLetter(rawNote).length + getRawAccidental(rawNote).length)
}
