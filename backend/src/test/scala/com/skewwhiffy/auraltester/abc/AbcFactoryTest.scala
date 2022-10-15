package com.skewwhiffy.auraltester.abc

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval}
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

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

  @ParameterizedTest
  @ValueSource(ints = Array(2, 3, 6, 7))
  def canInstantiateMajorInterval(degree: Int): Unit = {
    val expected = Interval.major(degree)

    val actual = AbcFactory.interval(degree.toString)

    assertThat(actual).isEqualTo(expected)
  }

  @ParameterizedTest
  @ValueSource(ints = Array(1, 4, 5, 8))
  def canInstantiatePerfectInterval(degree: Int): Unit = {
    val expected = Interval.perfect(degree)

    val actual = AbcFactory.interval(degree.toString)

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  def canInstantiateMinorThird(): Unit = {
    val expected = Interval.minor(3)

    val actual = AbcFactory.interval("3-")

    assertThat(actual).isEqualTo(expected)
  }
}
