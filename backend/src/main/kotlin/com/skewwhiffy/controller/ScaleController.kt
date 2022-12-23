package com.skewwhiffy.controller

import com.skewwhiffy.dto.ScaleResponse
import com.skewwhiffy.notation.factory.InternalNotationFactory
import com.skewwhiffy.notation.factory.ScaleTypeFactory
import com.skewwhiffy.notation.model.Key
import com.skewwhiffy.notation.model.scale.ScaleDirection
import com.skewwhiffy.service.AbcService
import com.skewwhiffy.service.ScaleService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScaleController(
  private val abcService: AbcService,
  private val internalNotationFactory: InternalNotationFactory,
  private val scaleService: ScaleService,
  private val scaleTypeFactory: ScaleTypeFactory,
) {
  @RequestMapping("/api/scale")
  fun get(
    clef: String,
    note: String,
    scaleType: String,
    direction: String,
  ): ScaleResponse {
    val clefObject = internalNotationFactory.clef(clef)
    val noteObject = internalNotationFactory.getNote(note).note
    val (scaleTypeObject, isMinor) = when (scaleType) {
      "major" -> Pair(scaleTypeFactory.major, false)
      "minor-harmonic" -> Pair(scaleTypeFactory.minorHarmonic, true)
      "minor-melodic" -> Pair(scaleTypeFactory.minorMelodic, true)
      else -> throw IllegalArgumentException("Unrecognized scale type: '$scaleType'")
    }
    val directionObject = when (direction) {
      "ascending" -> ScaleDirection.ASCENDING
      "descending" -> ScaleDirection.DESCENDING
      else -> throw IllegalArgumentException("Unrecognized direction: '$direction'")
    }
    val scale = scaleService.getScale(clefObject, noteObject, scaleTypeObject, directionObject)
    val key = scale.lowestNote.note.let { Key(it, isMinor) }
    return ScaleResponse(
      abcService.getAbc(clefObject, scale),
      abcService.getAbc(clefObject, scale, key)
    )
  }
}
