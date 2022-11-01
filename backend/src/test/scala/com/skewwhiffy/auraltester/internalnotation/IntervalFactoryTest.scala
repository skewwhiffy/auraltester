package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.notes.Interval
import com.skewwhiffy.auraltester.testutils.MockInstantiation
import org.mockito.InjectMocks
import org.scalatest.funsuite.AnyFunSuite

class IntervalFactoryTest extends AnyFunSuite with MockInstantiation {
  @InjectMocks
  private var intervalFactory: IntervalFactory = _

  List(2, 3, 6, 7).foreach(it => {
    test(s"can instantiate major $it") {
      val expected = Interval.major(it).up

      val actual = intervalFactory.getDirectedInterval(it.toString)

      assert(actual == expected)
    }
  })

  test("when invalid deviation then throws") {
    assertThrows[IllegalArgumentException] {
      intervalFactory.getDirectedInterval("5*")
    }
  }

  List(1, 4, 5, 8).foreach(degree => {
    test(s"can instantiate perfect $degree") {
      val expected = Interval.perfect(degree).up

      val actual = intervalFactory.getDirectedInterval(degree.toString)

      assert(actual == expected)
    }
  })

  test("can instantiate minor third") {
    val expected = Interval.minor(3).up

    val actual = intervalFactory.getDirectedInterval("3-")

    assert(actual == expected)
  }
}
