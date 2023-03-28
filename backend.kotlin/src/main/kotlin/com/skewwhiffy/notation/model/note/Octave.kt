package com.skewwhiffy.notation.model.note

data class Octave(val offsetFromDefault: Int) {
  companion object {
    val default: Octave = Octave(0)
  }

  val up: Octave get() = Octave(offsetFromDefault + 1)

  val down: Octave get() = Octave(offsetFromDefault - 1)

  operator fun compareTo(other: Octave): Int =
    offsetFromDefault.compareTo(other.offsetFromDefault)
}