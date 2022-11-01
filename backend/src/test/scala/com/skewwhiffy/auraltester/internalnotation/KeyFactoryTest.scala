package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.mockito.{InjectMocks, Mock}
import org.scalatest.funsuite.AnyFunSuite

class KeyFactoryTest extends AnyFunSuite with MockInstantiation {
  @Mock
  private val noteFactory: NoteFactory = null
  @InjectMocks
  private val keyFactory: KeyFactory = null

  test("can instantiate major key") {
    val rawKey = "a#"
    val expectedNote = TestData.random.note
    when(noteFactory.getNote("A#")).thenReturn(expectedNote)

    val actual = keyFactory.getKey(rawKey)

    assert(actual.note == expectedNote)
    assert(!actual.isMinor)
  }

  test("recognises m prefix for minor") {
    val rawKey = "abm"
    val expectedNote = TestData.random.note
    when(noteFactory.getNote("Ab")).thenReturn(expectedNote)

    val actual = keyFactory.getKey(rawKey)

    assert(actual.note == expectedNote)
    assert(actual.isMinor)
  }

  test("recognises minor suffix for minor") {
    val rawKey = "bx minor"
    val expectedNote = TestData.random.note
    when(noteFactory.getNote("Bx")).thenReturn(expectedNote)

    val actual = keyFactory.getKey(rawKey)

    assert(actual.note == expectedNote)
    assert(actual.isMinor)
  }

}
