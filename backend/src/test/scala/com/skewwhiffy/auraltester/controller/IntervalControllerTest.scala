package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Octave}
import com.skewwhiffy.auraltester.scales.{IntervalNotes, Key}
import com.skewwhiffy.auraltester.services.{AbcService, IntervalService}
import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.mockito.ArgumentMatchers.{eq => meq}
import org.mockito.{ArgumentCaptor, InjectMocks, Mock}
import org.scalatest.funsuite.AnyFunSuite

class IntervalControllerTest extends AnyFunSuite with MockInstantiation {
  @Mock
  private val abcService: AbcService = null
  @Mock
  private val internalNotationFactory: InternalNotationFactory = null
  @Mock
  private val intervalService: IntervalService = null
  @InjectMocks
  private val intervalController: IntervalController = null

  List(1, 4, 5, 8).foreach(degree =>
    Map(("perfect", ""), ("diminished", "-"), ("augmented", "+"))
      .foreach(testCase => test(degree, testCase))
  )

  List(2, 3, 6, 7).foreach(degree =>
    Map(("major", ""), ("minor", "-"), ("diminished", "--"), ("augmented", "+"))
      .foreach(testCase => test(degree, testCase))
  )

  def test(degree: Int, testCase: (String, String)): Unit = {
    test(s"can get ${testCase._1} $degree") {
      val clef = TestData.random.string
      val bottomNote = TestData.random.string
      val intervalQuality = testCase._1
      val intervalSize = degree
      val keySignature = TestData.random.string
      val clefObject = TestData.random.clef
      val bottomNoteObject = TestData.random.absoluteNote
      val keySignatureObject = TestData.random.key
      val keySignatureNote = new AbsoluteNote(keySignatureObject.note, Octave.default)
      val intervalQualitySuffix = testCase._2
      val expectedIntervalNotation = s"+$intervalSize$intervalQualitySuffix"
      val interval = TestData.random.interval
      val intervalNotes = mock[IntervalNotes]
      val keySignatureObjectCaptor = ArgumentCaptor.forClass(classOf[Key])
      val abc = TestData.random.string
      when(internalNotationFactory.clef(clef)).thenReturn(clefObject)
      when(internalNotationFactory.getNote(bottomNote)).thenReturn(bottomNoteObject)
      when(internalNotationFactory.getDirectedInterval(expectedIntervalNotation)).thenReturn(interval.up)
      when(internalNotationFactory.getNote(keySignature)).thenReturn(keySignatureNote)
      when(intervalService.getInterval(clefObject, bottomNoteObject.note, interval))
        .thenReturn(intervalNotes)
      when(abcService.getAbc(meq(clefObject), meq(intervalNotes), keySignatureObjectCaptor.capture()))
        .thenReturn(abc)

      val actual = intervalController.get(clef, bottomNote, intervalQuality, intervalSize, keySignature)

      assert(actual.abc == abc)
      assert(keySignatureObjectCaptor.getValue.asInstanceOf[Key].note == keySignatureNote.note)
    }
  }
}
