package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.controller.dto.IntervalResponse
import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.Interval
import com.skewwhiffy.auraltester.scales.Key
import com.skewwhiffy.auraltester.services.{AbcService, IntervalService}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, RestController}

import scala.util.chaining.scalaUtilChainingOps

@RestController
@RequestMapping(path = Array("/api/interval"))
class IntervalController(
  @Autowired private val abcService: AbcService,
  @Autowired private val internalNotationFactory: InternalNotationFactory,
  @Autowired private val intervalService: IntervalService
) {
  @GetMapping
  def get(
    @RequestParam(required = true) clef: String,
    @RequestParam(required = true) bottomNote: String,
    @RequestParam(required = true) intervalQuality: String,
    @RequestParam(required = true) intervalSize: Int,
    @RequestParam(required = false) keySignature: String
  ): IntervalResponse = {
    val clefObject = internalNotationFactory.clef(clef)
    val bottomNoteObject = internalNotationFactory.getNote(bottomNote).note
    val intervalQualitySuffix = intervalQuality match {
      case "perfect" | "major" => ""
      case "minor" => "-"
      case "diminished" => if (Interval.perfectDegrees.contains(intervalSize)) "-" else "--"
      case "augmented" => "+"
    }
    val interval = internalNotationFactory.getDirectedInterval(s"+$intervalSize$intervalQualitySuffix").interval
    val keyNote = internalNotationFactory.getNote(keySignature).note
    val keySignatureObject = new Key(keyNote)
    val intervalNotes = intervalService.getInterval(clefObject, bottomNoteObject, interval)
    abcService.getAbc(clefObject, intervalNotes, keySignatureObject)
      .pipe(it => new IntervalResponse(it))
  }
}
