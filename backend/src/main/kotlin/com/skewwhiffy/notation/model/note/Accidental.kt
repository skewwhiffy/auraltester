package com.skewwhiffy.notation.model.note

data class Accidental(private val offset: Int) {
  companion object {
    val natural: Accidental = Accidental(0)
    val flat: Accidental = Accidental(-1)
    val sharp: Accidental = Accidental(1)
  }

  val abc = when {
    offset == 0 -> ""
    offset < 0 -> "_".repeat(-offset)
    else -> "^".repeat(offset)
  }

  val displayString: String = when {
    offset < 0 -> "b".repeat(-offset)
    offset % 2 == 0 -> "x".repeat(offset / 2)
    else -> "x".repeat((offset - 1) / 2) + "#"
  }

  val flat: Accidental
    get() = Accidental(offset - 1)

  val sharp: Accidental
    get() = Accidental(offset + 1)
}