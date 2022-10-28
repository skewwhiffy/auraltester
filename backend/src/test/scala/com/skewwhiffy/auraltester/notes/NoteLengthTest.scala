package com.skewwhiffy.auraltester.notes

import com.skewwhiffy.auraltester.fractions.Fraction
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NoteLengthTest {
  @Test
  def correctlyAddsDot(): Unit = {
    val original = NoteLength.crotchet
    val expected = NoteLength(Fraction(3, 8))

    val actual = original.dotted

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  def canInstantiateSpecializedNoteLengths(): Unit = {
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

    assertThat(breve.abc).isEqualTo("2")
    assertThat(semibreve.abc).isEqualTo("1")
    assertThat(minim.abc).isEqualTo("1/2")
    assertThat(crotchet.abc).isEqualTo("1/4")
    assertThat(quaver.abc).isEqualTo("1/8")
    assertThat(semiquaver.abc).isEqualTo("1/16")
    assertThat(demisemiquaver.abc).isEqualTo("1/32")
    assertThat(hemidemisemiquaver.abc).isEqualTo("1/64")
  }
}