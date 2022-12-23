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

  /*
  it should "instantiate minor harmonic scale" in {
    val expected = internalNotation.getNotes("D E F G A Bb c# d")

    val actual = new Scale(
      internalNotation.getNote("D"),
      scaleTypeFactory.minorHarmonic,
      ScaleDirection.ascending
    )

    assert(actual.notes == expected)
  }

  it should "instantiate minor melodic ascending scale" in {
    val expected = internalNotation.getNotes("E F# G A B c# d# e")

    val actual = new Scale(
      internalNotation.getNote("E"),
      scaleTypeFactory.minorMelodic,
      ScaleDirection.ascending
    )

    assert(actual.notes == expected)
  }

  it should "instantiate minor melodic descending scale" in {
    val expected = internalNotation.getNotes("a g f e d c B A")

    val actual = new Scale(
      internalNotation.getNote("A"),
      scaleTypeFactory.minorMelodic,
      ScaleDirection.descending
    )

    assert(actual.notes == expected)
  }
}
   */
}