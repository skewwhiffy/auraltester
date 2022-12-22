package com.skewwhiffy.notation.model

data class Accidental(private val offset: Int) {
  companion object {
    val natural: Accidental = Accidental(0)
    /*
    val flat: Accidental = Accidental(-1)
    val sharp: Accidental = Accidental(1)
     */
  }

  val abc: String = when {
    offset == 0 -> ""
    offset < 0 -> "_".repeat(-offset)
    else -> "^".repeat(offset)
  }

  /*

  lazy val displayString: String = offset match {
    case 0 => ""
    case it if it < 0 => "b".repeat(-it)
    case it if it % 2 == 0 => "x".repeat(it / 2)
    case it => "x".repeat((it - 1) / 2) + "#"
  }
*/

  val flat: Accidental
    get() = Accidental(offset - 1)

  val sharp: Accidental
    get() = Accidental(offset + 1)
}