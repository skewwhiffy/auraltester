package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.model.IntervalResponse
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Octave}
import com.skewwhiffy.auraltester.scales.{IntervalNotes, Key}
import com.skewwhiffy.auraltester.services.{AbcService, IntervalService}
import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.mockito.ArgumentMatchers.{eq => meq}
import org.mockito.{ArgumentCaptor, InjectMocks, Mock}
import org.scalatestplus.play.PlaySpec
import play.api.http.Status.OK
import play.api.libs.json.{JsSuccess, Json}
import play.api.test.FakeRequest
import play.api.test.Helpers.{GET, contentAsJson, contentType, defaultAwaitTimeout, status}

import scala.util.chaining.scalaUtilChainingOps

class IntervalControllerTest extends PlaySpec with MockInstantiation {
  @Mock
  private val abcService: AbcService = null
  @Mock
  private val internalNotationFactory: InternalNotationFactory = null
  @Mock
  private val intervalService: IntervalService = null
  @InjectMocks
  private val intervalController: IntervalController = null

  def test(degree: Int, testCase: (String, String)): Unit = {
    val intervalQuality = testCase._1
    val intervalQualitySuffix = testCase._2
    val clef = TestData.random.string
    val bottomNote = TestData.random.string
    val intervalSize = degree
    val keySignature = TestData.random.string
    val clefObject = TestData.random.clef
    val bottomNoteObject = TestData.random.absoluteNote
    val keySignatureObject = TestData.random.key
    val keySignatureNote = AbsoluteNote(keySignatureObject.note, Octave.default)
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

    val actual = intervalController
      .index(clef, bottomNote, intervalQuality, intervalSize, Some(keySignature))
      .apply(FakeRequest(GET, "/api/interval"))

    status(actual) mustBe OK
    contentType(actual) mustBe Some("application/json")
    val deserialized = contentAsJson(actual)
      .pipe { it => Json.format[IntervalResponse].reads(it) }
      .pipe {
        case it: JsSuccess[IntervalResponse] => it.value
        case _ => fail()
      }
    deserialized.abc mustBe abc
    keySignatureObjectCaptor.getValue.asInstanceOf[Key].note mustBe keySignatureNote.note
  }

  "/api/interval GET" should {
    "instantiate 1, 4, 5, 8 as perfect, diminished and augmented" in {
      List(1, 4, 5, 8).flatMap(degree =>
        Map(("perfect", ""), ("diminished", "-"), ("augmented", "+"))
          .map(testCase => test(degree, testCase)))
    }

    "instantiate 2, 3, 6, 7 as major, minor, diminished and augmented" in {
      List(2, 3, 6, 7).foreach(degree =>
        Map(("major", ""), ("minor", "-"), ("diminished", "--"), ("augmented", "+"))
          .foreach(testCase => test(degree, testCase))
      )
    }
  }

}
