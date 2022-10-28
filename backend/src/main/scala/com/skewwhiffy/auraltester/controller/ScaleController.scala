package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.controller.dto.ScaleResponse
import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.scales.ScaleType
import com.skewwhiffy.auraltester.services.ScaleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

import scala.util.chaining.scalaUtilChainingOps

@RestController
@RequestMapping(path = Array("/api/scale"))
class ScaleController(@Autowired private val scaleService: ScaleService) {
  @GetMapping(path = Array("/{rawClef}/{rawNote}/{rawScaleType}"))
  def get(
    @PathVariable rawClef: String,
    @PathVariable rawNote: String,
    @PathVariable rawScaleType: String
  ): ScaleResponse = {
    val clef = InternalNotationFactory.clef(rawClef)
    val note = InternalNotationFactory.note(rawNote).note
    val scaleType = rawScaleType match {
      case "major" => ScaleType.major
      case "minor-harmonic" => ScaleType.minorHarmonic
      case "minor-melodic-ascending" => ScaleType.minorMelodicAscending
      case "minor-melodic-descending" => ScaleType.minorMelodicDescending
      case _ => throw new IllegalArgumentException(s"Unrecognized scale type: '$rawScaleType'")
    }
    val scale = scaleService.getScale(clef, note, scaleType)
    s"""
          }
X:1
T:${scale.displayName}
K:clef=${clef.abc}
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