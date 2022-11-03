package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.notes.AbsoluteNote
import com.skewwhiffy.auraltester.scales.Key
import com.skewwhiffy.auraltester.testutils.MockInstantiation
import org.mockito.InjectMocks
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class NoteFactoryTest extends AnyFlatSpec with should.Matchers with MockInstantiation {
  @InjectMocks
  private var noteFactory: NoteFactory = _

  it should "can instantiate middle C" in {
    val expected = AbsoluteNote.middleC

    val actual = noteFactory.getAbsoluteNote("C")

    assert(actual == expected)
  }

  it should "can instantiate note above middle C" in {
    val expected = "c''"

    val actual = noteFactory.getAbsoluteNote(expected)

    assert(actual.abc(Key.cMajor) == expected)
  }

  it should "can instantiate note below middle C" in {
    val internalNotation = "Dx#,,,"
    val expected = "^^^D,,,"

    val actual = noteFactory.getAbsoluteNote(internalNotation)

    assert(actual.abc(Key.cMajor) == expected)
  }

  it should "when note name invalid then throws" in {
    assertThrows[IllegalArgumentException] {
      noteFactory.getAbsoluteNote("H")
    }
  }
}
