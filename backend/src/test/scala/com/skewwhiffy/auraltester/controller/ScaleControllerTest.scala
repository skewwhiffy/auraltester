package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Octave}
import com.skewwhiffy.auraltester.scales.{Key, Scale, ScaleDirection, ScaleType, ScaleTypeFactory}
import com.skewwhiffy.auraltester.services.{AbcService, ScaleService}
import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.mockito.{InjectMocks, Mock}
import org.scalatest.funsuite.AnyFunSuite

class ScaleControllerTest extends AnyFunSuite with MockInstantiation {
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
  ).foreach(testCase =>
    test(s"scale type ${testCase._1} is parsed correctly") {
      val clefString = TestData.random.string
      val noteString = TestData.random.string
      val direction = "ascending"
      val abcWithoutKeySignature = TestData.random.string
      val abcWithKeySignature = TestData.random.string
      val clefObject = mock[Clef]
      val scale = mock[Scale]
      val key = new Key(TestData.random.note)
      val scaleLowestNote = new AbsoluteNote(key.note, Octave.default)
      val scaleType = mock[ScaleType]
      when(internalNotationFactory.clef(clefString)).thenReturn(clefObject)
      when(internalNotationFactory.getNote(noteString)).thenReturn(scaleLowestNote)
      when(testCase._2()).thenReturn(scaleType)
      when(scale.lowestNote).thenReturn(new AbsoluteNote(key.note, Octave.default))
      when(scaleService.getScale(clefObject, key.note, scaleType, ScaleDirection.ascending)).thenReturn(scale)
      when(abcService.getAbc(clefObject, scale)).thenReturn(abcWithoutKeySignature)
      when(abcService.getAbc(clefObject, scale, key)).thenReturn(abcWithKeySignature)

      val actual = scaleController.get(
        clefString,
        noteString,
        testCase._1,
        direction
      )

      assert(actual.withKeySignature == abcWithKeySignature)
      assert(actual.withoutKeySignature == abcWithoutKeySignature)
    }
  )

}