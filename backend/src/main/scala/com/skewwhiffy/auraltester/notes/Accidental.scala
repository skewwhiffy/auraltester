package com.skewwhiffy.auraltester.notes

object Accidental {
  lazy val NATURAL: Accidental = Accidental(0)
  lazy val FLAT: Accidental = Accidental(-1)
  lazy val SHARP: Accidental = Accidental(1)
}

private class Accidental(offset: Int) {
  lazy val displayString: String = offset match {
    case 0 => ""
    case it if it < 0 => "b".repeat(-it)
    case it if it % 2 == 0 => "x".repeat(it / 2)
    case it => "x".repeat((it - 1) / 2) + "#"
  }

  lazy val flat: Accidental = Accidental(offset - 1)

  lazy val sharp: Accidental = Accidental(offset + 1)
}
