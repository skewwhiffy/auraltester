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
}
