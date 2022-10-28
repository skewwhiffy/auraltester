package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.fractions.Fraction
import scala.util.chaining._

object NoteLength:
  lazy val crotchet: NoteLength = NoteLength(Fraction(1, 4))

class NoteLength(private val length: Fraction):
  lazy val dotted: NoteLength = Fraction(3, 2)
    .pipe(it => it*length)
    .pipe(it => NoteLength(it))

  override def equals(obj: Any): Boolean = obj match
    case it: NoteLength => it.length == length
    case _ => false
