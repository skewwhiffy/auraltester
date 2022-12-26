package com.skewwhiffy.notation.model.note

import com.skewwhiffy.notation.model.interval.Interval
import com.skewwhiffy.notation.model.key.Key
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.fail

class AbsoluteNoteTest {
  @Test
  fun `instantiates middle C`() {
    val expected = "C"

    val actual = AbsoluteNote.middleC

    assertThat(actual.abc(Key.cMajor)).isEqualTo(expected)
  }

  @Test
  fun `adds major and perfect intervals`() {
    val intervals = listOf(
      Interval.perfect(1),
      Interval.major(2),
      Interval.major(3),
      Interval.perfect(4),
      Interval.perfect(5),
      Interval.major(6),
      Interval.major(7),
      Interval.perfect(8)
    )
    val start = AbsoluteNote.middleC
    val expected = listOf("C", "D", "E", "F", "G", "A", "B", "c")

    testGeneric(start, intervals, expected)
  }

  @Test
  fun `adds minor intervals`() {
    val start = AbsoluteNote.middleC + Interval.major(6)
    val intervals = listOf(
      Interval.perfect(1),
      Interval.minor(2),
      Interval.minor(3),
      Interval.perfect(4),
      Interval.perfect(5),
      Interval.minor(6),
      Interval.minor(7),
      Interval.perfect(8)
    )
    val expected = listOf("A", "_B", "c", "d", "e", "f", "g", "a")

    testGeneric(start, intervals, expected)
  }

  @Test
  fun `adds diminished and augmented intervals`() {
    val intervals = listOf(
      Interval.diminished(3), Interval.diminished(4), Interval.augmented(5), Interval.augmented(6)
    )
    val start = AbsoluteNote.middleC
    val expected = listOf("__E", "_F", "^G", "^A")

    testGeneric(start, intervals, expected)
  }

  @Test
  fun `does not add compound intervals (yet)`() {
    val start = AbsoluteNote.middleC

    assertThrows<IllegalArgumentException> { start + Interval.major(9) }
  }

  @Test
  fun `applies up interval`() {
    val interval = Interval.minor(3).up
    val start = AbsoluteNote.middleC
    val expected = "_E"

    val actual = start.apply(interval)

    assertThat(actual.abc(Key.cMajor)).isEqualTo(expected)
  }

  @Test
  fun `applies down interval`() {
    val interval = Interval.minor(3)
    val directedInterval = interval.down
    val start = AbsoluteNote.middleC
    val expected = "A,"

    val actualWithApply = start.apply(directedInterval)
    val actualWithSubtract = start - interval

    assertThat(actualWithApply.abc(Key.cMajor)).isEqualTo(expected)
    assertThat(actualWithSubtract.abc(Key.cMajor)).isEqualTo(expected)
  }

  @Test
  fun `not subtract compound intervals yet`() {
    val interval = Interval.major(9)

    assertThrows<IllegalArgumentException> { AbsoluteNote.middleC - interval }
  }

  @Test
  fun `equivalent notes are equal`() {
    fun getNote(): AbsoluteNote = AbsoluteNote(Note.d.sharp, Octave.default.up)

    val first = getNote()
    val second = getNote()

    assertThat(first).isEqualTo(second)
    assertThat(first <= second).isTrue
    assertThat(first >= second).isTrue
  }

  @Test
  fun `absolute note is not equal to non absolute note`() {
    fun getNote(): AbsoluteNote = AbsoluteNote.middleC

    val someOtherObject = Note.c

    @Suppress("AssertBetweenInconvertibleTypes") assertThat(getNote()).isNotEqualTo(someOtherObject)
  }

  @Test
  fun `toString returns abc`() {
    val note = AbsoluteNote.middleC

    assertThat(note.toString()).isEqualTo(note.abc(Key.cMajor))
  }

  @Test
  fun `non equivalent notes in same octave are comparable`() {
    val lower = AbsoluteNote(Note.d, Octave.default)
    val higher = AbsoluteNote(Note.b, Octave.default)

    assertThat(lower < higher).isTrue
    assertThat(lower > higher).isFalse
    assertThat(lower <= higher).isTrue
    assertThat(lower >= higher).isFalse
  }

  private fun testGeneric(
    start: AbsoluteNote, intervals: List<Interval>, expectedAbcs: List<String>
  ) {
    if (intervals.size != expectedAbcs.size) {
      fail("Need expectation size = interval list size")
    }
    intervals.zip(expectedAbcs).forEach {
      testGeneric(start, it.first, it.second)
    }
  }

  private fun testGeneric(
    start: AbsoluteNote, interval: Interval, expectedAbc: String
  ) {
    val actual = (start + interval).abc(Key.cMajor)

    assertThat(actual).isEqualTo(expectedAbc)
  }
}