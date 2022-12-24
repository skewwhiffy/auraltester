package com.skewwhiffy.controller

import com.skewwhiffy.dto.IntervalResponse
import com.skewwhiffy.notation.factory.InternalNotationFactory
import com.skewwhiffy.notation.model.interval.Interval
import com.skewwhiffy.notation.model.key.Key
import com.skewwhiffy.service.AbcService
import com.skewwhiffy.service.IntervalService
import org.springframework.web.bind.annotation.RequestMapping
import java.lang.IllegalArgumentException

class IntervalController(
  private val abcService: AbcService,
  private val internalNotationFactory: InternalNotationFactory,
  private val intervalService: IntervalService,
) {
  @RequestMapping("/api/interval")
  fun get(
    clef: String,
    bottomNote: String,
    intervalQuality: String,
    intervalSize: Int,
    keySignature: String?,
  ): IntervalResponse {
    val clefObject = internalNotationFactory.clef(clef)
    val bottomNoteObject = internalNotationFactory.getNote(bottomNote).note
    val intervalQualitySuffix = when (intervalQuality) {
      "perfect", "major" -> ""
      "minor" -> "-"
      "diminished" -> if (Interval.perfectDegrees.contains(intervalSize)) "-" else "--"
      "augmented" -> "+"
      else -> throw IllegalArgumentException("Unrecognized quality type: '$intervalQuality'")
    }
    val interval = internalNotationFactory
      .getDirectedInterval("+$intervalSize$intervalQualitySuffix")
      .interval
    val keyNote = internalNotationFactory
      .getNote(keySignature ?: "C").note
    val keySignatureObject = Key(keyNote)
    val intervalNotes = intervalService.getInterval(clefObject, bottomNoteObject, interval)
    return abcService.getAbc(clefObject, intervalNotes, keySignatureObject)
      .let(::IntervalResponse)
  }
}
