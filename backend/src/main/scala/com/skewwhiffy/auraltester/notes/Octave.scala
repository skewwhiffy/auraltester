package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.scales.Key

object Octave {
  lazy val default: Octave = new Octave(0)
}

class Octave(val offsetFromDefault: Int) {
  def getAbc(note: Note, key: Key): String = {
    offsetFromDefault match {
      case 0 => note.abc
      case it if it > 0 => s"${note.abc.toLowerCase}${"'".repeat(it - 1)}"
      case it if it < 0 => s"${note.abc}${",".repeat(-it)}"
    }
  }

  lazy val up: Octave = new Octave(offsetFromDefault + 1)

  lazy val down: Octave = new Octave(offsetFromDefault - 1)

  def >(other: Octave): Boolean = offsetFromDefault > other.offsetFromDefault

  def <(other: Octave): Boolean = offsetFromDefault < other.offsetFromDefault

  def >=(other: Octave): Boolean = offsetFromDefault > other.offsetFromDefault || this == other

  def <=(other: Octave): Boolean = offsetFromDefault < other.offsetFromDefault || this == other

  override def equals(obj: Any): Boolean = obj match {
    case it: Octave => it.offsetFromDefault == offsetFromDefault
    case _ => false
  }
}
