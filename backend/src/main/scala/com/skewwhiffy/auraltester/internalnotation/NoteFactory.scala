package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, Note, Octave}
import scala.util.chaining.scalaUtilChainingOps

import scala.annotation.tailrec

class NoteFactory(private val abc: String) {
  lazy val absoluteNote: AbsoluteNote = new AbsoluteNote(note, octave)

  private lazy val note: Note = new Note(noteName, accidental)

  private lazy val noteName: String = {
    rawNote.toUpperCase match {
      case it if it >= "A" && it <= "G" => it
      case it => throw new IllegalArgumentException(s"'$it' is not a valid note name'")
    }
  }

  private lazy val accidental: Accidental = rawAccidental
    .toCharArray
    .map {
      case 'x' => 2
      case '#' => 1
      case 'b' => -1
      case it => throw new IllegalArgumentException(s"Not valid accidental: '$it'")
    }
    .sum
    .pipe(it => new Accidental(it))

  private val octave: Octave = rawOctave
    .toCharArray
    .map {
      case '\'' => 1
      case ',' => -1
      case it => throw new IllegalArgumentException(s"Not valid octave indicator: '$it'")
    }
    .sum
    .pipe(it => it + (if (rawNote.toLowerCase == rawNote) 1 else 0))
    .pipe(it => new Octave(it))

  private lazy val rawNote: String = abc.substring(0, 1)

  private lazy val rawAccidental: String = {
    @tailrec
    def getRawAccidental(str: String): String = {
      if (str.endsWith("'") || str.endsWith(",")) getRawAccidental(str.substring(0, str.length - 1))
      else str
    }

    getRawAccidental(abc.substring(1))
  }

  private lazy val rawOctave: String = abc
    .substring(rawNote.length + rawAccidental.length)
}
