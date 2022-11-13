package com.skewwhiffy.auraltester.notes

object Accidental {
  lazy val natural: Accidental = new Accidental(0)
  lazy val flat: Accidental = new Accidental(-1)
  lazy val sharp: Accidental = new Accidental(1)
}

class Accidental(private val offset: Int) {
  lazy val abc: String = offset match {
    case 0 => ""
    case it if it < 0 => "_".repeat(-it)
    case it => "^".repeat(it)
  }

  lazy val displayString: String = offset match {
    case 0 => ""
    case it if it < 0 => "b".repeat(-it)
    case it if it % 2 == 0 => "x".repeat(it / 2)
    case it => "x".repeat((it - 1) / 2) + "#"
  }

  lazy val flat: Accidental = new Accidental(offset - 1)

  lazy val sharp: Accidental = new Accidental(offset + 1)

  override def equals(obj: Any): Boolean = obj match {
    case other: Accidental => other.offset == offset
    case _ => false
  }
}