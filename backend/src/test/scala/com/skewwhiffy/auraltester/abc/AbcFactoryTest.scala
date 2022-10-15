package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.notes.AbsoluteNote
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AbcFactoryTest {
  @Test
  def canInstantiateMiddleC(): Unit = {
    val expected = AbsoluteNote.middleC

    val actual = AbcFactory.note("C")

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  def canInstantiateNoteAboveMiddleC(): Unit = {
    val expected = "c''"

    val actual = AbcFactory.note(expected)

    assertThat(actual.abc).isEqualTo(expected)
  }

  @Test
  def canInstantiateNoteBelowMiddleC(): Unit = {
    val expected = "Dx#,,,"

    val actual = AbcFactory.note(expected)

    assertThat(actual.abc).isEqualTo(expected)
  }
}
