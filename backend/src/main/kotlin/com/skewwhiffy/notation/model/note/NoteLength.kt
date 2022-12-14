package com.skewwhiffy.notation.model.note

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

  val dotted
    get() = NoteLength((Fraction(3, 2) * length))

  val abc: String = length.topHeavyString
}