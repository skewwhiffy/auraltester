package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.clefs.ClefFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval}
import com.skewwhiffy.auraltester.scales.Key
import org.scalamock.scalatest.MockFactory
import org.scalatest.Outcome
import org.scalatest.funsuite.AnyFunSuite

class InternalNotationFactoryTest extends AnyFunSuite with MockFactory{
  private var clefFactory: ClefFactory = _
  private var noteFactory: NoteFactory = _
  private var intervalFactory: IntervalFactory = _
  private var internalNotationFactory: InternalNotationFactory = _

  override def withFixture(test: NoArgTest): Outcome = {
    clefFactory = mock[ClefFactory]
    noteFactory = mock[NoteFactory]
    intervalFactory = mock[IntervalFactory]
    internalNotationFactory = new InternalNotationFactory(clefFactory, noteFactory, intervalFactory)
    test()
  }

  test("can instantiate middle C") {
    val expected = AbsoluteNote.middleC

    val actual = internalNotationFactory.getNote("C")

    assert(actual == expected)
  }

  test("can instantiate note above middle C") {
    val expected = "c''"

    val actual = internalNotationFactory.getNote(expected)

    assert(actual.abc(Key.cMajor) == expected)
  }

  test("can instantiate note below middle C") {
    val internalNotation = "Dx#,,,"
    val expected = "^^^D,,,"

    val actual = internalNotationFactory.getNote(internalNotation)

    assert(actual.abc(Key.cMajor) == expected)
  }

  test("when note name invalid then throws") {
    assertThrows[IllegalArgumentException] {
      internalNotationFactory.getNote("H")
    }
  }

  List(2, 3, 6, 7).foreach(it => {
    test(s"can instantiate major $it") {
      val expected = Interval.major(it).up

      val actual = internalNotationFactory.getDirectedInterval(it.toString)

      assert(actual == expected)
    }
  })

  test("when invalid deviation then throws") {
    assertThrows[IllegalArgumentException] {
      internalNotationFactory.getDirectedInterval("5*")
    }
  }

  List(1, 4, 5, 8).foreach(degree => {
    test(s"can instantiate perfect $degree") {
      val expected = Interval.perfect(degree).up

      val actual = internalNotationFactory.getDirectedInterval(degree.toString)

      assert(actual == expected)
    }
  })

  test("can instantiate minor third") {
    val expected = Interval.minor(3).up

    val actual = internalNotationFactory.getDirectedInterval("3-")

    assert(actual == expected)
  }
}
