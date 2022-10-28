package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.controller.dto.ScaleResponse
import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.scales.{ScaleDirection, ScaleType}
import com.skewwhiffy.auraltester.services.ScaleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, RestController}

import scala.util.chaining.scalaUtilChainingOps

@RestController
@RequestMapping(path = Array("/api/scale"))
class ScaleController(@Autowired private val scaleService: ScaleService) {
  @GetMapping
  def get(
    @RequestParam(required = true) clef: String,
    @RequestParam(required = true) note: String,
    @RequestParam(required = true) scaleType: String,
    @RequestParam(required = true) direction: String,
    @RequestParam(required = true) withKeySignature: Boolean
  ): ScaleResponse = {
    val clefObject = InternalNotationFactory.clef(clef)
    val noteObject = InternalNotationFactory.note(note).note
    val scaleTypeObject = scaleType match {
      case "major" => ScaleType.major
      case "minor-harmonic" => ScaleType.minorHarmonic
      case "minor-melodic" => ScaleType.minorMelodic
      case _ => throw new IllegalArgumentException(s"Unrecognized scale type: '$scaleType'")
    }
    val directionObject = direction match {
      case "ascending" => ScaleDirection.ascending
      case "descending" => ScaleDirection.descending
      // TODO: Check this throws with non valid
    }
    val scale = scaleService.getScale(clefObject, noteObject, scaleTypeObject, directionObject)
    s"""
          }
X:1
T:${scale.displayName}
K:clef=${clefObject.abc}
L:1
${scale.abc}
    """
      .stripMargin
      .pipe(it => {
        println(it)
        new ScaleResponse(it)
      })
  }
}