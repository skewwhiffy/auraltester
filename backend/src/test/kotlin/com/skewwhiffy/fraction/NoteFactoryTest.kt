package com.skewwhiffy.fraction

import com.skewwhiffy.notation.factory.NoteFactory
import com.skewwhiffy.notation.model.AbsoluteNote
import com.skewwhiffy.notation.model.Key
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class NoteFactoryTest {
  @InjectMocks
  private lateinit var noteFactory: NoteFactory

  @Test
  fun `instantiates middle C`() {
    val expected = AbsoluteNote.middleC

    val actual = noteFactory.getAbsoluteNote("C")

    assertThat(actual).isEqualTo(expected)
  }

  @Test
  fun `instantiate note above middle C`() {
    val expected = "c''"

    val actual = noteFactory.getAbsoluteNote(expected)

    assertThat(actual.abc(Key.cMajor)).isEqualTo(expected)
  }

  @Test
  fun `instantiate note below middle C`() {
    val internalNotation = "Dx#,,,"
    val expected = "^^^D,,,"

    val actual = noteFactory.getAbsoluteNote(internalNotation)

    assertThat(actual.abc(Key.cMajor)).isEqualTo(expected)
  }

  @Test
  fun `throw when note name invalid`() {
    assertThrows<IllegalArgumentException> {
      noteFactory.getAbsoluteNote("H")
    }
  }
}