package com.skewwhiffy.notation.model

data class Octave(val offsetFromDefault: Int) {
  companion object {
    val default: Octave = Octave(0)
  }

  /*
  lazy val up: Octave = Octave(offsetFromDefault + 1)

  lazy val down: Octave = Octave(offsetFromDefault - 1)

  def >(other: Octave): Boolean = offsetFromDefault > other.offsetFromDefault

  def <(other: Octave): Boolean = offsetFromDefault < other.offsetFromDefault

  def >=(other: Octave): Boolean = offsetFromDefault > other.offsetFromDefault || this == other

  def <=(other: Octave): Boolean = offsetFromDefault < other.offsetFromDefault || this == other
}
   */
}