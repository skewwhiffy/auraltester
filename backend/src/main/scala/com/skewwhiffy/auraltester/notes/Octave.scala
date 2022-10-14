package com.skewwhiffy.auraltester.notes

object Octave {
  val DEFAULT: Octave = Octave(0)
}

private class Octave(val offsetFromDefault: Int) {
  def getAbc(note: Note): String = {
    if (offsetFromDefault == 0) {
      return note.displayString
    }
    if (offsetFromDefault > 0) {
      val apostropheSuffix = "'".repeat(offsetFromDefault - 1)
      return note.displayString.toLowerCase() + apostropheSuffix
    }
    val commaSuffix = ",".repeat(-offsetFromDefault)
    note.displayString + commaSuffix
  }

  def getUp: Octave = {
    Octave(offsetFromDefault + 1)
  }

  def getDown: Octave = {
    Octave(offsetFromDefault - 1)
  }
}
