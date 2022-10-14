package com.skewwhiffy.auraltester.notes

object Octave {
  lazy val DEFAULT: Octave = Octave(0)
}

private class Octave(offsetFromDefault: Int) {
  lazy val getAbc: Note => String = note => offsetFromDefault match {
    case 0 => note.displayString
    case it if it > 0 => s"${note.displayString.toLowerCase}${"'".repeat(it - 1)}"
    case it if it < 0 => s"${note.displayString}${",".repeat(-it)}"
  }

  lazy val up: Octave = Octave(offsetFromDefault + 1)

  lazy val down: Octave = Octave(offsetFromDefault - 1)
}
