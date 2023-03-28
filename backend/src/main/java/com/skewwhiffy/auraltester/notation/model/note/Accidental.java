package com.skewwhiffy.auraltester.notation.model.note;

public record Accidental(int offset) {
    /*

data class Accidental(private val offset: Int) {

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
     */

    public static Accidental getNatural() {
        return new Accidental(0);
    }

    public static Accidental getFlat() {
        return new Accidental(-1);
    }

    public static Accidental getSharp() {
        return new Accidental(1);
    }
}
