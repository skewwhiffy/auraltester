package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.clefs.ClefFactory
import com.skewwhiffy.auraltester.notes.Interval
import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.mockito.Mockito.{mock, when}
import org.mockito.{InjectMocks, Mock}
import org.scalatest.funsuite.AnyFunSuite

class InternalNotationFactoryTest extends AnyFunSuite with MockInstantiation {
  @Mock
  private var clefFactory: ClefFactory = _
  @Mock
  private var noteFactory: NoteFactory = _
  @Mock
  private var intervalFactory: IntervalFactory = _
  @InjectMocks
  private var internalNotationFactory: InternalNotationFactory = _

  test("when getNote then proxies to noteFactory") {
    val rawNote = TestData.random.string
    val expected = TestData.random.absoluteNote
    when(noteFactory.getAbsoluteNote(rawNote)).thenReturn(expected)

    val actual = internalNotationFactory.getNote(rawNote)

    assert(actual == expected)
  }

  test("when getNotes then proxies to noteFactory") {
    val rawNotes = Range(0, 10).map(_ => TestData.random.string)
    val expected = rawNotes.map(_ => TestData.random.absoluteNote)
    rawNotes.zip(expected).foreach(it => {
      when(noteFactory.getAbsoluteNote(it._1)).thenReturn(it._2)
    })

    val actual = internalNotationFactory.getNotes(rawNotes.mkString(" "))

    assert(actual == expected)
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
