package com.skewwhiffy.notation.model

import com.skewwhiffy.fraction.Fraction

data class NoteLength(private val length: Fraction) {
  @Suppress("unused", "SpellCheckingInspection")
  companion object {
    val breve: NoteLength = NoteLength(2)
    val semibreve: NoteLength = NoteLength(1)
    val minim: NoteLength = NoteLength(Fraction(1, 2))
    val crotchet: NoteLength = NoteLength(Fraction(1, 4))
    val quaver: NoteLength = NoteLength(Fraction(1, 8))
    val semiquaver: NoteLength = NoteLength(Fraction(1, 16))
    val demisemiquaver: NoteLength = NoteLength(Fraction(1, 32))
    val hemidemisemiquaver: NoteLength = NoteLength(Fraction(1, 64))
  }

  constructor(length: Int) : this(Fraction(length, 1))

  /*
class:
lazy val dotted: NoteLength = Fraction(3, 2)
  .pipe(it => it * length)
  .pipe(it => NoteLength(it))
*/

  val abc: String = length.topHeavyString
  /*
  override def equals(obj: Any): Boolean = obj match {
    case it: NoteLength => it.length == length
    case _ => false
  }
}
   */
}