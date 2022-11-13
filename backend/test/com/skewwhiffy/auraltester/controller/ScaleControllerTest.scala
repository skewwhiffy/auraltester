package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.model.ScaleResponse
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Octave}
import com.skewwhiffy.auraltester.scales._
import com.skewwhiffy.auraltester.services.{AbcService, ScaleService}
import com.skewwhiffy.auraltester.testutils.{MockInstantiation, TestData}
import org.mockito.ArgumentMatchers.{eq => meq}
import org.mockito.{ArgumentCaptor, InjectMocks, Mock}
import org.scalatestplus.play.PlaySpec
import play.api.http.Status.OK
import play.api.libs.json.{JsSuccess, Json}
import play.api.test.FakeRequest
import play.api.test.Helpers.{GET, contentAsJson, contentType, defaultAwaitTimeout, status}

import scala.util.chaining.scalaUtilChainingOps

class ScaleControllerTest extends PlaySpec with MockInstantiation {
  private val getRequest = FakeRequest(GET, "/api/scale")
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

  "/api/scale GET" should {
    "parse major, minor harmonic and minor melodic scales both ascending and descending" in {
      Map(
        ("major", () => scaleTypeFactory.major),
        ("minor-harmonic", () => scaleTypeFactory.minorHarmonic),
        ("minor-melodic", () => scaleTypeFactory.minorMelodic)
      ).foreach(scaleTypeTestCase =>
        Map(
          ("ascending", ScaleDirection.ascending),
          ("descending", ScaleDirection.descending)
        ).foreach(directionTestCase => {
          val scaleTypeString = scaleTypeTestCase._1
          val direction = directionTestCase._1
          val directionObject = directionTestCase._2
          val getScale = scaleTypeTestCase._2

          val clefString = TestData.random.string
          val noteString = TestData.random.string
          val abcWithoutKeySignature = TestData.random.string
          val abcWithKeySignature = TestData.random.string
          val scaleLowestNote = TestData.random.absoluteNote
          val key = new Key(scaleLowestNote.note, scaleTypeString != "major")
          val clefObject = mock[Clef]
          val scale = mock[Scale]
          val scaleType = mock[ScaleType]
          val keyCaptor: ArgumentCaptor[Key] = ArgumentCaptor.forClass(classOf[Key])
          when(internalNotationFactory.clef(clefString)).thenReturn(clefObject)
          when(internalNotationFactory.getNote(noteString)).thenReturn(scaleLowestNote)
          when(getScale()).thenReturn(scaleType)
          when(scale.lowestNote).thenReturn(AbsoluteNote(key.note, Octave.default))
          when(scaleService.getScale(clefObject, key.note, scaleType, directionObject)).thenReturn(scale)
          when(abcService.getAbc(clefObject, scale)).thenReturn(abcWithoutKeySignature)
          when(abcService.getAbc(meq(clefObject), meq(scale), keyCaptor.capture())).thenReturn(abcWithKeySignature)

          val actual = scaleController.index(
            clefString,
            noteString,
            scaleTypeString,
            direction
          ).apply(getRequest)

          status(actual) mustBe OK
          contentType(actual) mustBe Some("application/json")
          keyCaptor.getValue mustBe key
          val deserialized = contentAsJson(actual)
            .pipe { it => Json.format[ScaleResponse].reads(it) }
            .pipe { case it: JsSuccess[ScaleResponse] => it.value }
          deserialized.withKeySignature mustBe abcWithKeySignature
          deserialized.withoutKeySignature mustBe abcWithoutKeySignature
        })
      )
    }

    "throw when an invalid scale type is requested" in {
      val note = TestData.random.string
      when(internalNotationFactory.getNote(note)).thenReturn(TestData.random.absoluteNote)

      assertThrows[IllegalArgumentException] {
        scaleController.index("alto", note, "not-a-scale", "ascending").apply(getRequest)
      }
    }

    "throw when an invalid direction is requested" in {
      val note = TestData.random.string
      when(internalNotationFactory.getNote(note)).thenReturn(TestData.random.absoluteNote)
      when(scaleTypeFactory.major).thenReturn(TestData.random.scaleType)

      assertThrows[IllegalArgumentException] {
        scaleController.index("bass", note, "major", "stationary").apply(getRequest)
      }
    }
  }
}