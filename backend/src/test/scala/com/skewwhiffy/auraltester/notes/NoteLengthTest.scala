package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.fractions.Fraction
import org.scalatest.funsuite.AnyFunSuite

class NoteLengthTest extends AnyFunSuite {
  test("correctly adds dot") {
    val original = NoteLength.crotchet
    val expected = NoteLength(Fraction(3, 8))

    val actual = original.dotted

    assert(actual == expected)
  }

  test("can instantiate specialized note lengths") {
    val breve = NoteLength.breve
    val semibreve = NoteLength.semibreve
    val minim = NoteLength.minim
    val crotchet = NoteLength.crotchet
    val quaver = NoteLength.quaver
    val semiquaver = NoteLength.semiquaver
    //noinspection SpellCheckingInspection
    val demisemiquaver = NoteLength.demisemiquaver
    //noinspection SpellCheckingInspection
    val hemidemisemiquaver = NoteLength.hemidemisemiquaver

    assert(breve.abc == "2")
    assert(semibreve.abc == "1")
    assert(minim.abc == "1/2")
    assert(crotchet.abc == "1/4")
    assert(quaver.abc == "1/8")
    assert(semiquaver.abc == "1/16")
    assert(demisemiquaver.abc == "1/32")
    assert(hemidemisemiquaver.abc == "1/64")
  }
}