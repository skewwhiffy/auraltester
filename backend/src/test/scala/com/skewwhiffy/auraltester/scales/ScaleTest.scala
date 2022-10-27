package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote
import com.skewwhiffy.auraltester.scales.Scale
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScaleTest {
  @Test
  def canInstantiateMajorScale(): Unit = {
    val expected = InternalNotationFactory.notes("C D E F G A B c")

    val actual = Scale(AbsoluteNote.middleC, ScaleType.major)

    assertThat(actual.notes).isEqualTo(expected)
  }

  @Test
  def canInstantiateMinorHarmonicScale(): Unit = {
    val expected = InternalNotationFactory.notes("D E F G A Bb c# d")

    val actual = Scale(InternalNotationFactory.note("D"), ScaleType.minorHarmonic)

    assertThat(actual.notes).isEqualTo(expected)
  }

  @Test
  def canInstantiateMinorMelodicAscendingScale(): Unit = {
    val expected = InternalNotationFactory.notes("E F# G A B c# d# e")

    val actual = Scale(InternalNotationFactory.note("E"), ScaleType.minorMelodicAscending)

    assertThat(actual.notes).isEqualTo(expected)
  }

  @Test
  def canInstantiateMinorMelodicDescendingScale(): Unit = {
    val expected = InternalNotationFactory.notes("a g f e d c B A")

    val actual = Scale(InternalNotationFactory.note("a"), ScaleType.minorMelodicDescending)

    assertThat(actual.notes).isEqualTo(expected)
  }
}
