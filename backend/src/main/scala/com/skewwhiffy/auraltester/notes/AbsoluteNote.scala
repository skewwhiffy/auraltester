package com.skewwhiffy.auraltester.notes

object AbsoluteNote {
  val MIDDLE_C: AbsoluteNote = AbsoluteNote(Note.C, Octave.DEFAULT)
}

private class AbsoluteNote(val note: Note, val octave: Octave) {
  def add(interval: Interval): AbsoluteNote = {
    val defaultNote: AbsoluteNote = interval.getDegree match {
      case 1 => this
      case 2 => addMajorSecond()
      case 3 => addMajorSecond().addMajorSecond()
      case 4 => add(Interval.major(3)).addMinorSecond()
      case 5 => add(Interval.perfect(4)).addMajorSecond()
      case 6 => add(Interval.perfect(5)).addMajorSecond()
      case 7 => add(Interval.major(6)).addMajorSecond()
      case 8 => AbsoluteNote(note, octave.getUp)
      case _ => throw new IllegalArgumentException()
    }

    if (interval.getDeviation < 0) {
      return List.range(0, -interval.getDeviation)
        .foldRight(defaultNote)((_, note) => note.getFlat)
    }
    if (interval.getDeviation > 0) {
      return List.range(0, interval.getDeviation)
        .foldRight(defaultNote)((_, note) => note.getSharp)
    }
    defaultNote
  }

  def getAbc: String = {
    octave.getAbc(note)
  }

  def getSharp: AbsoluteNote = {
    AbsoluteNote(note.getSharp, octave)
  }

  private def getFlat: AbsoluteNote = {
    AbsoluteNote(note.getFlat, octave)
  }

  private def addMinorSecond(): AbsoluteNote = {
    val majorSecondUp = addMajorSecond()
    AbsoluteNote(majorSecondUp.note.getFlat, majorSecondUp.octave)
  }

  def addMajorSecond(): AbsoluteNote = {
    val newOctave = if "B" == note.getNoteName then octave.getUp else octave
    new AbsoluteNote(note.addMajorSecond(), newOctave)
  }
}