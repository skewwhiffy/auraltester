package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.clefs.ClefFactory
import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.mockito.{InjectMocks, Mock}
import org.scalatest.funsuite.AnyFunSuite

class InternalNotationFactoryTest extends AnyFunSuite with MockInstantiation {
  @Mock
  private val clefFactory: ClefFactory = null
  @Mock
  private val intervalFactory: IntervalFactory = null
  @Mock
  private val keyFactory: KeyFactory = null
  @Mock
  private val noteFactory: NoteFactory = null
  @InjectMocks
  private val internalNotationFactory: InternalNotationFactory = null

  List("treble", "alto", "tenor", "bass").foreach(it => {
    test(s"when $it then proxies to clefFactory") {
      val expected = TestData.random.clef
      val method = classOf[ClefFactory].getMethod(it)
      when(method.invoke(clefFactory)).thenReturn(expected)

      val actual = internalNotationFactory.clef(it)

      assert(actual == expected)
    }
  })

  test("when clef name invalid then blows up") {
    assertThrows[IllegalArgumentException] {
      internalNotationFactory.clef("not a clef name")
    }
  }

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

  test("when getDirectedInterval then proxies to intervalFactory") {
    val rawInterval = TestData.random.string
    val expected = TestData.random.directedInterval
    when(intervalFactory.getDirectedInterval(rawInterval)).thenReturn(expected)

    val actual = internalNotationFactory.getDirectedInterval(rawInterval)

    assert(actual == expected)
  }

  test("when getKey then proxies to keyFactory") {
    val rawKey = TestData.random.string
    val expected = TestData.random.key
    when(keyFactory.getKey(rawKey)).thenReturn(expected)

    val actual = internalNotationFactory.getKey(rawKey)

    assert(actual == expected)
  }
}
