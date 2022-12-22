package com.skewwhiffy.notation.model

data class Octave(val offsetFromDefault: Int) {
  companion object {
    val default: Octave = Octave(0)
  }

  val up: Octave get() = Octave(offsetFromDefault + 1)

  val down: Octave get() = Octave(offsetFromDefault - 1)
  /*

  def >(other: Octave): Boolean = offsetFromDefault > other.offsetFromDefault

  def <(other: Octave): Boolean = offsetFromDefault < other.offsetFromDefault

  def >=(other: Octave): Boolean = offsetFromDefault > other.offsetFromDefault || this == other

  def <=(other: Octave): Boolean = offsetFromDefault < other.offsetFromDefault || this == other
}
   */
}