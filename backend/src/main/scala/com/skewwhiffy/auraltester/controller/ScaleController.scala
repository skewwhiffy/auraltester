package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.abc.SingleLineAbc
import com.skewwhiffy.auraltester.controller.dto.ScaleResponse
import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.NoteLength
import com.skewwhiffy.auraltester.scales.{Key, ScaleDirection, ScaleType, ScaleTypeFactory}
import com.skewwhiffy.auraltester.services.ScaleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, RestController}

import scala.util.chaining.scalaUtilChainingOps

@RestController
@RequestMapping(path = Array("/api/scale"))
class ScaleController(
  @Autowired private val internalNotationFactory: InternalNotationFactory,
  @Autowired private val scaleService: ScaleService,
  @Autowired private val scaleTypeFactory: ScaleTypeFactory
) {
  @GetMapping
  def get(
    @RequestParam(required = true) clef: String,
    @RequestParam(required = true) note: String,
    @RequestParam(required = true) scaleType: String,
    @RequestParam(required = true) direction: String,
    @RequestParam(required = true) withKeySignature: Boolean
  ): ScaleResponse = {
    val clefObject = internalNotationFactory.clef(clef)
    val noteObject = internalNotationFactory.getNote(note).note
    val scaleTypeObject = scaleType match {
      case "major" => scaleTypeFactory.major
      case "minor-harmonic" => scaleTypeFactory.minorHarmonic
      case "minor-melodic" => scaleTypeFactory.minorMelodic
      case _ => throw new IllegalArgumentException(s"Unrecognized scale type: '$scaleType'")
    }
    val directionObject = direction match {
      case "ascending" => ScaleDirection.ascending
      case "descending" => ScaleDirection.descending
      // TODO: Check this throws with non valid
    }
    val scale = scaleService.getScale(clefObject, noteObject, scaleTypeObject, directionObject)
    val key = new Key(scale.lowestNote.note, scaleType != "major")
    val displayName = s"${scale.displayName} $direction"
    val baseAbcObject = new SingleLineAbc(displayName, clefObject, NoteLength.semibreve, scale.notes)
    val abcObjectWithKey = if (withKeySignature) baseAbcObject.includeKeySignature(key) else baseAbcObject
    abcObjectWithKey
      .abc
      .pipe(it => {
        println(it)
        it
      })
      .pipe(it => new ScaleResponse(it))
  }
}