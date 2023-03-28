package com.skewwhiffy.notation.model.scale

import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ScaleTest {
  @Test
  fun `instantiate major scale`() {
    val expected = TestData.noteFactories.internalNotation.getNotes("C D E F G A B c")

    val actual = Scale(
      AbsoluteNote.middleC,
      TestData.noteFactories.scaleTypeFactory.major,
      ScaleDirection.ASCENDING
    )

    assertThat(actual.notes).isEqualTo(expected)
  }

  @Test
  fun `instantiate minor harmonic scale`() {
    val expected = TestData.noteFactories.internalNotation.getNotes("D E F G A Bb c# d")

    val actual = Scale(
      TestData.noteFactories.internalNotation.getNote("D"),
      TestData.noteFactories.scaleTypeFactory.minorHarmonic,
      ScaleDirection.ASCENDING
    )

    assertThat(actual.notes).isEqualTo(expected)
  }

  @Test
  fun `instantiate minor melodic ascending scale`() {
    val expected = TestData.noteFactories.internalNotation.getNotes("E F# G A B c# d# e")

    val actual = Scale(
      TestData.noteFactories.internalNotation.getNote("E"),
      TestData.noteFactories.scaleTypeFactory.minorMelodic,
      ScaleDirection.ASCENDING
    )

    assertThat(actual.notes).isEqualTo(expected)
  }

  @Test
  fun `instantiate minor melodic descending scale`() {
    val expected = TestData.noteFactories.internalNotation.getNotes("a g f e d c B A")

    val actual = Scale(
      TestData.noteFactories.internalNotation.getNote("A"),
      TestData.noteFactories.scaleTypeFactory.minorMelodic,
      ScaleDirection.DESCENDING
    )

    assertThat(actual.notes).isEqualTo(expected)
  }
}