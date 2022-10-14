package com.skewwhiffy.auraltester.notes

object Accidental {
  val NATURAL: Accidental = Accidental(0)
  val FLAT: Accidental = Accidental(-1)
  val SHARP:Accidental = Accidental(1)
}
private class Accidental(offset: Int) {
  def getDisplayString: String = {
    if (offset == 0) {
      return ""
    }
    if (offset < 0) {
      return "b".repeat(-offset)
    }
    if (offset % 2 == 0) {
      return "x".repeat(offset / 2)
    }
    "x".repeat((offset - 1) / 2) + "#"
  }

  def getFlat: Accidental = {
    Accidental(offset - 1)
  }

  def getSharp: Accidental = {
    Accidental(offset + 1)
  }

}
