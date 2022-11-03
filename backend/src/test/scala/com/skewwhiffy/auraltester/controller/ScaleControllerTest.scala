package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Octave}
import com.skewwhiffy.auraltester.scales.{Key, Scale, ScaleDirection, ScaleType, ScaleTypeFactory}
import com.skewwhiffy.auraltester.services.{AbcService, ScaleService}
import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.mockito.{ArgumentCaptor, InjectMocks, Mock}
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should
import org.mockito.ArgumentMatchers.{eq => meq}

class ScaleControllerTest extends AnyFlatSpec with MockInstantiation with should.Matchers {
  @Mock
  private val abcService: AbcService = null
  @Mock
  private val internalNotationFactory: InternalNotationFactory = null
  @Mock
  private val scaleService: ScaleService = null
  @Mock
  private val scaleTypeFactory: ScaleTypeFactory = null
  @InjectMocks
  private val scaleController: ScaleController = null

  Map(
    ("major", () => scaleTypeFactory.major),
    ("minor-harmonic", () => scaleTypeFactory.minorHarmonic),
    ("minor-melodic", () => scaleTypeFactory.minorMelodic)
  ).foreach(scaleTypeTestCase =>
    Map(
      ("ascending", ScaleDirection.ascending),
      ("descending", ScaleDirection.descending)
    ).foreach(directionTestCase =>
      it should s"scale type ${scaleTypeTestCase._1} ${directionTestCase._1} is parsed correctly" in {
        val clefString = TestData.random.string
        val noteString = TestData.random.string
        val direction = directionTestCase._1
        val abcWithoutKeySignature = TestData.random.string
        val abcWithKeySignature = TestData.random.string
        val scaleLowestNote = TestData.random.absoluteNote
        val key = new Key(scaleLowestNote.note, scaleTypeTestCase._1 != "major")
        val clefObject = mock[Clef]
        val scale = mock[Scale]
        val scaleType = mock[ScaleType]
        val keyCaptor: ArgumentCaptor[Key] = ArgumentCaptor.forClass(classOf[Key])
        when(internalNotationFactory.clef(clefString)).thenReturn(clefObject)
        when(internalNotationFactory.getNote(noteString)).thenReturn(scaleLowestNote)
        when(scaleTypeTestCase._2()).thenReturn(scaleType)
        when(scale.lowestNote).thenReturn(new AbsoluteNote(key.note, Octave.default))
        when(scaleService.getScale(clefObject, key.note, scaleType, directionTestCase._2)).thenReturn(scale)
        when(abcService.getAbc(clefObject, scale)).thenReturn(abcWithoutKeySignature)
        when(abcService.getAbc(meq(clefObject), meq(scale), keyCaptor.capture())).thenReturn(abcWithKeySignature)

        val actual = scaleController.get(
          clefString,
          noteString,
          scaleTypeTestCase._1,
          direction
        )

        assert(keyCaptor.getValue == key)
        assert(actual.withKeySignature == abcWithKeySignature)
        assert(actual.withoutKeySignature == abcWithoutKeySignature)
      }
    )
  )

  it should "invalid scale type throws" in {
    val note = TestData.random.string
    when(internalNotationFactory.getNote(note)).thenReturn(TestData.random.absoluteNote)

    assertThrows[IllegalArgumentException] {
      scaleController.get("alto", note, "not-a-scale", "ascending")
    }
  }

  it should "invalid direction throws" in {
    val note = TestData.random.string
    when(internalNotationFactory.getNote(note)).thenReturn(TestData.random.absoluteNote)
    when(scaleTypeFactory.major).thenReturn(TestData.random.scaleType)

    assertThrows[IllegalArgumentException] {
      scaleController.get("bass", note, "major", "stationary")
    }
  }

}