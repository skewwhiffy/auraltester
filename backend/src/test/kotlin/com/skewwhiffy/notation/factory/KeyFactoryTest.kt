package com.skewwhiffy.notation.factory

import org.junit.jupiter.api.Test

class KeyFactoryTest {
  @Test
  fun dummy(): Unit = TODO()
}
/*

class KeyFactoryTest extends AnyFlatSpec with MockInstantiation {
  @Mock
  private val noteFactory: NoteFactory = null
  @InjectMocks
  private val keyFactory: KeyFactory = null

  it should "instantiate major key" in {
    val rawKey = "a#"
    val expectedNote = TestData.random.note
    when(noteFactory.getNote("A#")).thenReturn(expectedNote)

    val actual = keyFactory.getKey(rawKey)

    assert(actual.note == expectedNote)
    assert(!actual.isMinor)
  }

  it should "recognise m prefix for minor" in {
    val rawKey = "abm"
    val expectedNote = TestData.random.note
    when(noteFactory.getNote("Ab")).thenReturn(expectedNote)

    val actual = keyFactory.getKey(rawKey)

    assert(actual.note == expectedNote)
    assert(actual.isMinor)
  }

  it should "recognise minor suffix for minor" in {
    val rawKey = "bx minor"
    val expectedNote = TestData.random.note
    when(noteFactory.getNote("Bx")).thenReturn(expectedNote)

    val actual = keyFactory.getKey(rawKey)

    assert(actual.note == expectedNote)
    assert(actual.isMinor)
  }

}
 */