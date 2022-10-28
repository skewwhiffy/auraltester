package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.fractions.Fraction
import scala.util.chaining._

object NoteLength {
  lazy val breve: NoteLength = NoteLength(2)
  lazy val semibreve: NoteLength = NoteLength(1)
  lazy val minim: NoteLength = NoteLength(Fraction(1, 2))
  lazy val crotchet: NoteLength = NoteLength(Fraction(1, 4))
  lazy val quaver: NoteLength = NoteLength(Fraction(1, 8))
  lazy val semiquaver: NoteLength = NoteLength(Fraction(1, 16))
  //noinspection SpellCheckingInspection
  lazy val demisemiquaver: NoteLength = NoteLength(Fraction(1, 32))
  //noinspection SpellCheckingInspection
  lazy val hemidemisemiquaver: NoteLength = NoteLength(Fraction(1, 64))

  def apply(length: Int): NoteLength = NoteLength(Fraction(length, 1))

  def apply(length: Fraction): NoteLength = new NoteLength(length)
}

class NoteLength(private val length: Fraction) {
  lazy val dotted: NoteLength = Fraction(3, 2)
    .pipe(it => it * length)
    .pipe(it => NoteLength(it))

  lazy val abc: String = length.topHeavyString

  override def equals(obj: Any): Boolean = obj match {
    case it: NoteLength => it.length == length
    case _ => false
  }
}