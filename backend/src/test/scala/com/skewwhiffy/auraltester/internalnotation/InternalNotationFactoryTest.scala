package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval}
import com.skewwhiffy.auraltester.scales.Key
import org.scalatest.Outcome
import org.scalatest.funsuite.AnyFunSuite

class InternalNotationFactoryTest extends AnyFunSuite {
  var internalNotationFactory: InternalNotationFactory = _

  override def withFixture(test: NoArgTest): Outcome = {
    internalNotationFactory = new InternalNotationFactory()
    test()
  }

  test("can instantiate middle C") {
    val expected = AbsoluteNote.middleC

    val actual = internalNotationFactory.note("C")

    assert(actual == expected)
  }

  test("can instantiate note above middle C") {
    val expected = "c''"

    val actual = internalNotationFactory.note(expected)

    assert(actual.abc(Key.cMajor) == expected)
  }

  test("can instantiate note below middle C") {
    val internalNotation = "Dx#,,,"
    val expected = "^^^D,,,"

    val actual = internalNotationFactory.note(internalNotation)

    assert(actual.abc(Key.cMajor) == expected)
  }

  test("when note name invalid then throws") {
    assertThrows[IllegalArgumentException] {
      internalNotationFactory.note("H")
    }
  }

  List(2, 3, 6, 7).foreach(it => {
    test(s"can instantiate major $it") {
      val expected = Interval.major(it).up

      val actual = internalNotationFactory.directedInterval(it.toString)

      assert(actual == expected)
    }
  })

  test("when invalid deviation then throws") {
    assertThrows[IllegalArgumentException] {
      internalNotationFactory.directedInterval("5*")
    }
  }

  List(1, 4, 5, 8).foreach(degree => {
    test(s"can instantiate perfect $degree") {
      val expected = Interval.perfect(degree).up

      val actual = internalNotationFactory.directedInterval(degree.toString)

      assert(actual == expected)
    }
  })

  test("can instantiate minor third") {
    val expected = Interval.minor(3).up

    val actual = internalNotationFactory.directedInterval("3-")

    assert(actual == expected)
  }
}
