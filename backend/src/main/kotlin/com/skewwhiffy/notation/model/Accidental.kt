package com.skewwhiffy.notation.model

data class Accidental(private val offset: Int) {
  companion object {
    val natural: Accidental = Accidental(0)
    val flat: Accidental = Accidental(-1)
    val sharp: Accidental = Accidental(1)
  }
  /*

case class Accidental(private val offset: Int) {
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

  lazy val flat: Accidental = Accidental(offset - 1)

  lazy val sharp: Accidental = Accidental(offset + 1)
}
   */
}