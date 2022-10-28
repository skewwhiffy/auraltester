package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval}
import org.scalatest.funsuite.AnyFunSuite

class InternalNotationFactoryTest extends AnyFunSuite {
  test("can instantiate middle C") {
    val expected = AbsoluteNote.middleC

    val actual = InternalNotationFactory.note("C")

    assert(actual == expected)
  }

  test("can instantiate note above middle C") {
    val expected = "c''"

    val actual = InternalNotationFactory.note(expected)

    assert(actual.abc == expected)
  }

  test("can instantiate note below middle C") {
    val internalNotation = "Dx#,,,"
    val expected = "^^^D,,,"

    val actual = InternalNotationFactory.note(internalNotation)

    assert(actual.abc == expected)
  }

  test("when note name invalid then throws") {
    assertThrows[IllegalArgumentException] {
      InternalNotationFactory.note("H")
    }
  }

  List(2, 3, 6, 7).foreach(it => {
    test(s"can instantiate major $it") {
      val expected = Interval.major(it).up

      val actual = InternalNotationFactory.directedInterval(it.toString)

      assert(actual == expected)
    }
  })

  test("when invalid deviation then throws") {
    assertThrows[IllegalArgumentException] {
      InternalNotationFactory.directedInterval("5*")
    }
  }

  List(1, 4, 5, 8).foreach(degree => {
    test(s"can instantiate perfect $degree") {
      val expected = Interval.perfect(degree).up

      val actual = InternalNotationFactory.directedInterval(degree.toString)

      assert(actual == expected)
    }
  })

  test("can instantiate minor third") {
    val expected = Interval.minor(3).up

    val actual = InternalNotationFactory.directedInterval("3-")

    assert(actual == expected)
  }
}
