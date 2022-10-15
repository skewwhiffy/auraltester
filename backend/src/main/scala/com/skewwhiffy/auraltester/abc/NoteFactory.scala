package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, Note, Octave}
import scala.util.chaining._

import scala.annotation.tailrec

class NoteFactory(private val abc: String) {
  lazy val absoluteNote: AbsoluteNote = AbsoluteNote(note, octave)

  private lazy val note: Note = Note(noteName, accidental)

  private lazy val noteName: String = {
    rawNote.toUpperCase match
      case it if it >= "A" && it <= "G" => it
      case it => throw IllegalArgumentException(s"'$it' is not a valid note name'")
  }

  private lazy val accidental: Accidental = rawAccidental
    .toCharArray
    .map(it => it match
      case 'x' => 2
      case '#' => 1
      case 'b' => -1
      case _ => throw IllegalArgumentException(s"Not valid accidental: '$it'")
    )
    .sum
    .pipe(it => Accidental(it))

  private val octave: Octave = rawOctave
    .toCharArray
    .map(it => it match
      case '\'' => 1
      case ',' => -1
      case _ => throw IllegalArgumentException(s"Not valid octave indicator: '$it'")
    )
    .sum
    .pipe(it => it + (if rawNote.toLowerCase == rawNote then 1 else 0))
    .pipe(it => Octave(it))

  private lazy val rawNote: String = abc.substring(0, 1)

  private lazy val rawAccidental: String = {
    @tailrec
    def getRawAccidental(str: String): String = {
      if str.endsWith("'") || str.endsWith(",") then getRawAccidental(str.substring(0, str.length - 1))
      else str
    }

    getRawAccidental(abc.substring(1))
  }

  private lazy val rawOctave: String = abc
    .substring(rawNote.length + rawAccidental.length)
}
