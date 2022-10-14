package com.skewwhiffy.auraltester.notes

import scala.util.chaining._

object AbsoluteNote {
  lazy val MIDDLE_C: AbsoluteNote = AbsoluteNote(Note.C, Octave.DEFAULT)
}

private class AbsoluteNote(val note: Note, val octave: Octave) {
  lazy val add: Interval => AbsoluteNote = interval => {
    val defaultNote: AbsoluteNote = interval.degree match {
      case 1 => this
      case 2 => upMajorSecond
      case 3 => upMajorSecond.upMajorSecond
      case 4 => add(Interval.major(3)).upMinorSecond
      case 5 => add(Interval.perfect(4)).upMajorSecond
      case 6 => add(Interval.perfect(5)).upMajorSecond
      case 7 => add(Interval.major(6)).upMajorSecond
      case 8 => AbsoluteNote(note, octave.getUp)
      case _ => throw new IllegalArgumentException()
    }

    interval.deviation match {
      case it if it < 0 => List.range(0, -it).foldRight(defaultNote)((_, note) => note.flat)
      case it if it > 0 => List.range(0, it).foldRight(defaultNote)((_, note) => note.sharp)
      case 0 => defaultNote
    }
  }

  lazy val abc: String = octave.getAbc(note)

  private lazy val sharp = AbsoluteNote(note.getSharp, octave)

  private lazy val flat = AbsoluteNote(note.getFlat, octave)

  private lazy val upMinorSecond = upMajorSecond.pipe(it => AbsoluteNote(it.note.getFlat, it.octave))

  private lazy val upMajorSecond = (if "B" == note.noteName then octave.getUp else octave)
    .pipe(it => AbsoluteNote(note.addMajorSecond(), it))
}