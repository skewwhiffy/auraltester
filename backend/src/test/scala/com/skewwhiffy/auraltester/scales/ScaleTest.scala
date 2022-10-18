package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.abc.AbcFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote
import com.skewwhiffy.auraltester.scales.Scale
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScaleTest {
  @Test
  def canInstantiateMajorScale(): Unit = {
    val expected = AbcFactory.notes("C D E F G A B c")

    val actual = Scale.major(AbsoluteNote.middleC)

    assertThat(actual.notes).isEqualTo(expected)
  }

  @Test
  def canInstantiateMinorHarmonicScale(): Unit = {
    val expected = AbcFactory.notes("D E F G A Bb c# d")

    val actual = Scale.minor.harmonic(AbcFactory.note("D"))

    assertThat(actual.notes).isEqualTo(expected)
  }

  @Test
  def canInstantiateMinorMelodicAscendingScale(): Unit = {
    val expected = AbcFactory.notes("E F# G A B c# d# e")

    val actual = Scale.minor.melodic.ascending(AbcFactory.note("E"))

    assertThat(actual.notes).isEqualTo(expected)
  }

  @Test
  def canInstantiateMinorMelodicDescendingScale(): Unit = {
    val expected = AbcFactory.notes("a g f e d c B A")

    val actual = Scale.minor.melodic.descending(AbcFactory.note("a"))

    assertThat(actual.notes).isEqualTo(expected)
  }
}
