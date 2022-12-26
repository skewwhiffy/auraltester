package com.skewwhiffy.notation.factory

import com.skewwhiffy.test.util.TestData
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
class KeyFactoryTest {
  @Mock
  private lateinit var noteFactory: NoteFactory
  @InjectMocks
  private lateinit var keyFactory: KeyFactory

  @Test
  fun `instantiates major key`() {
    val rawKey = "a#"
    val expectedNote = TestData.random.note
    `when`(noteFactory.getNote("A#")).thenReturn(expectedNote)

    val actual = keyFactory.getKey(rawKey)

    assertThat(actual.note).isEqualTo( expectedNote)
    assertThat(actual.isMinor).isFalse
  }

  @Test
  fun `recognises m prefix for minor`() {
    val rawKey = "abm"
    val expectedNote = TestData.random.note
    `when`(noteFactory.getNote("Ab")).thenReturn(expectedNote)

    val actual = keyFactory.getKey(rawKey)

    assertThat(actual.note).isEqualTo(expectedNote)
    assertThat(actual.isMinor).isTrue
  }

  @Test
  fun `recognise minor suffix for minor`() {
    val rawKey = "bx minor"
    val expectedNote = TestData.random.note
    `when`(noteFactory.getNote("Bx")).thenReturn(expectedNote)

    val actual = keyFactory.getKey(rawKey)

    assertThat(actual.note).isEqualTo(expectedNote)
    assertThat(actual.isMinor).isTrue
  }
}