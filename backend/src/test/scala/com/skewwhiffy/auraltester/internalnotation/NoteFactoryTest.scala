package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.notes.AbsoluteNote
import com.skewwhiffy.auraltester.scales.Key
import org.scalatest.funsuite.AnyFunSuite

class NoteFactoryTest extends AnyFunSuite {
  private val noteFactory = new NoteFactory()

  test("can instantiate middle C") {
    val expected = AbsoluteNote.middleC

    val actual = noteFactory.getAbsoluteNote("C")

    assert(actual == expected)
  }

  test("can instantiate note above middle C") {
    val expected = "c''"

    val actual = noteFactory.getAbsoluteNote(expected)

    assert(actual.abc(Key.cMajor) == expected)
  }

  test("can instantiate note below middle C") {
    val internalNotation = "Dx#,,,"
    val expected = "^^^D,,,"

    val actual = noteFactory.getAbsoluteNote(internalNotation)

    assert(actual.abc(Key.cMajor) == expected)
  }

  test("when note name invalid then throws") {
    assertThrows[IllegalArgumentException] {
      noteFactory.getAbsoluteNote("H")
    }
  }
}
