package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.notes.Interval
import com.skewwhiffy.auraltester.testutils.MockInstantiation
import org.mockito.InjectMocks
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class IntervalFactoryTest extends AnyFlatSpec with should.Matchers with MockInstantiation {
  @InjectMocks
  private var intervalFactory: IntervalFactory = _

  List(2, 3, 6, 7).foreach(intervalDegree => {
    it should s"can instantiate major $intervalDegree" in {
      val expected = Interval.major(intervalDegree).up

      val actual = intervalFactory.getDirectedInterval(intervalDegree.toString)

      assert(actual == expected)
    }
  })

  it should "when invalid deviation then throws" in {
    assertThrows[IllegalArgumentException] {
      intervalFactory.getDirectedInterval("5*")
    }
  }

  List(1, 4, 5, 8).foreach(degree => {
    it should s"can instantiate perfect $degree" in {
      val expected = Interval.perfect(degree).up

      val actual = intervalFactory.getDirectedInterval(degree.toString)

      assert(actual == expected)
    }
  })

  it should "can instantiate minor third" in {
    val expected = Interval.minor(3).up

    val actual = intervalFactory.getDirectedInterval("3-")

    assert(actual == expected)
  }
}
