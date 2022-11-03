package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.clefs.ClefFactory
import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.mockito.{InjectMocks, Mock}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should

class InternalNotationFactoryTest extends AnyFlatSpec with should.Matchers with MockInstantiation {
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
    it should s"when $it then proxies to clefFactory" in {
      val expected = TestData.random.clef
      val method = classOf[ClefFactory].getMethod(it)
      when(method.invoke(clefFactory)).thenReturn(expected)

      val actual = internalNotationFactory.clef(it)

      assert(actual == expected)
    }
  })

  it should "when clef name invalid then blows up" in {
    assertThrows[IllegalArgumentException] {
      internalNotationFactory.clef("not a clef name")
    }
  }

  it should "when getNote then proxies to noteFactory" in {
    val rawNote = TestData.random.string
    val expected = TestData.random.absoluteNote
    when(noteFactory.getAbsoluteNote(rawNote)).thenReturn(expected)

    val actual = internalNotationFactory.getNote(rawNote)

    assert(actual == expected)
  }

  it should "when getNotes then proxies to noteFactory" in {
    val rawNotes = Range(0, 10).map(_ => TestData.random.string)
    val expected = rawNotes.map(_ => TestData.random.absoluteNote)
    rawNotes.zip(expected).foreach(it => {
      when(noteFactory.getAbsoluteNote(it._1)).thenReturn(it._2)
    })

    val actual = internalNotationFactory.getNotes(rawNotes.mkString(" "))

    assert(actual == expected)
  }

  it should "when getDirectedInterval then proxies to intervalFactory" in {
    val rawInterval = TestData.random.string
    val expected = TestData.random.directedInterval
    when(intervalFactory.getDirectedInterval(rawInterval)).thenReturn(expected)

    val actual = internalNotationFactory.getDirectedInterval(rawInterval)

    assert(actual == expected)
  }

  it should "when getKey then proxies to keyFactory" in {
    val rawKey = TestData.random.string
    val expected = TestData.random.key
    when(keyFactory.getKey(rawKey)).thenReturn(expected)

    val actual = internalNotationFactory.getKey(rawKey)

    assert(actual == expected)
  }
}
