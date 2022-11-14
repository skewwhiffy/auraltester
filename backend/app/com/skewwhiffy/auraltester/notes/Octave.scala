package com.skewwhiffy.auraltester.notes

object Octave {
  lazy val default: Octave = Octave(0)
}

case class Octave(offsetFromDefault: Int) {
  lazy val up: Octave = Octave(offsetFromDefault + 1)

  lazy val down: Octave = Octave(offsetFromDefault - 1)

  def >(other: Octave): Boolean = offsetFromDefault > other.offsetFromDefault

  def <(other: Octave): Boolean = offsetFromDefault < other.offsetFromDefault

  def >=(other: Octave): Boolean = offsetFromDefault > other.offsetFromDefault || this == other

  def <=(other: Octave): Boolean = offsetFromDefault < other.offsetFromDefault || this == other
}
