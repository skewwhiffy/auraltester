package com.skewwhiffy.notation.model

import com.skewwhiffy.fraction.Fraction

data class NoteLength(private val length: Fraction) {
  companion object {
    /*
  lazy val breve: NoteLength = NoteLength(2)
  */
    val semibreve: NoteLength = NoteLength(1)
  }
  constructor(length: Int): this(Fraction(length, 1))
    /*
  lazy val minim: NoteLength = NoteLength(Fraction(1, 2))
  lazy val crotchet: NoteLength = NoteLength(Fraction(1, 4))
  lazy val quaver: NoteLength = NoteLength(Fraction(1, 8))
  lazy val semiquaver: NoteLength = NoteLength(Fraction(1, 16))
  //noinspection SpellCheckingInspection
  lazy val demisemiquaver: NoteLength = NoteLength(Fraction(1, 32))
  //noinspection SpellCheckingInspection
  lazy val hemidemisemiquaver: NoteLength = NoteLength(Fraction(1, 64))

class:
  def apply(length: Int): NoteLength = NoteLength(Fraction(length, 1))

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