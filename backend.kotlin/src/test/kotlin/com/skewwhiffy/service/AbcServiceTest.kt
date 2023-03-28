package com.skewwhiffy.service

import com.skewwhiffy.notation.model.abc.SingleLineAbc
import com.skewwhiffy.notation.model.note.NoteLength
import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class AbcServiceTest {
  @InjectMocks
  private lateinit var abcService: AbcService

  @Test
  fun `gets correct ABC for scale`() {
    val clef = TestData.random.clef
    val scale = TestData.random.scale
    val expected = SingleLineAbc(
      "${scale.displayName} ${scale.direction.displayString}".titleCase,
      clef,
      NoteLength.semibreve,
      listOf(scale.notes)
    )

    val actual = abcService.getAbc(clef, scale)

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `gets correct ABC for scale with key signature`() {
    val clef = TestData.random.clef
    val scale = TestData.random.scale
    val key = TestData.random.key
    val expected = SingleLineAbc(
      "${scale.displayName} ${scale.direction.displayString} (with key signature)".titleCase,
      clef,
      NoteLength.semibreve,
      key,
      listOf(scale.notes)
    )

    val actual = abcService.getAbc(clef, scale, key)

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `gets correct ABC for clef`() {
    val clef = TestData.random.clef
    val expected = SingleLineAbc(
      "${clef.displayName} Clef Notes".titleCase,
      clef,
      NoteLength.semibreve,
      clef.notes.map { it.notes }
    )

    val actual = abcService.getAbc(clef)

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `gets correct ABC for interval`() {
    val clef = TestData.random.clef
    val intervalNotes = TestData.random.intervalNotes
    val key = TestData.random.key
    val expected = SingleLineAbc(
      intervalNotes.interval.displayString.titleCase,
      clef,
      NoteLength.semibreve,
      key,
      listOf(intervalNotes.notes)
    )

    val actual = abcService.getAbc(clef, intervalNotes, key)

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `gets correct ABC for key`() {
    val clef = TestData.random.clef
    val key = TestData.random.key
    val expected = SingleLineAbc(
      "${key.displayString} / ${key.relative.displayString}".titleCase,
      clef,
      NoteLength.semibreve,
      key,
      listOf(listOf())
    )

    val actual = abcService.getAbc(clef, key)

    assertThat(actual).isEqualTo(expected)
  }
}

private val String.titleCase
  get() = split(" ").joinToString(" ") { it.substring(0, 1).uppercase() + it.substring(1) }

