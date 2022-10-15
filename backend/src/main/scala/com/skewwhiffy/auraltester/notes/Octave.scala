package com.skewwhiffy.auraltester.notes

object Octave {
  lazy val default: Octave = Octave(0)
}

class Octave(val offsetFromDefault: Int) {
  lazy val getAbc: Note => String = note => offsetFromDefault match {
    case 0 => note.displayString
    case it if it > 0 => s"${note.displayString.toLowerCase}${"'".repeat(it - 1)}"
    case it if it < 0 => s"${note.displayString}${",".repeat(-it)}"
  }

  lazy val up: Octave = Octave(offsetFromDefault + 1)

  lazy val down: Octave = Octave(offsetFromDefault - 1)

  override def equals(obj: Any): Boolean = obj match
    case it: Octave => it.offsetFromDefault == offsetFromDefault
    case _ => false
}
