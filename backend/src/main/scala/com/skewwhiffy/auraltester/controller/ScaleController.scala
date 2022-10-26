package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.controller.dto.ScaleResponse
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval, Note}
import com.skewwhiffy.auraltester.scales.Scale
import org.springframework.http.{MediaType, ResponseEntity}
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}
import scala.util.chaining.*

@RestController
@RequestMapping(path = Array("/api/scale"))
class ScaleController:
  @GetMapping(path = Array("/{rawClef}/{rawNote}/{rawScaleType}"))
  def get(
    @PathVariable rawClef: String,
    @PathVariable rawNote: String,
    @PathVariable rawScaleType: String
  ): ScaleResponse = {
    val clef = InternalNotationFactory.clef(rawClef)
    val startingNote = getStartingNote(clef, rawNote, rawScaleType)
    val scale = rawScaleType.toLowerCase match
      case "major" => Scale.major(startingNote)
      case "minor-harmonic" => Scale.minor.harmonic(startingNote)
      case "minor-melodic-ascending" => Scale.minor.melodic.ascending(startingNote)
      case "minor-melodic-descending" => Scale.minor.melodic.descending(startingNote)
      case _ => throw IllegalArgumentException(s"Unrecognized scale type '$rawScaleType'")
    s"""
X:1
T:${scale.displayName}
K:clef=${clef.name}
L:1
${scale.notes.map(it => s"${it.abc}").mkString("")}
    """
      .stripMargin
      .pipe(it => ScaleResponse(it))
  }

  private def getStartingNote(clef: Clef, rawNote: String, rawScaleType: String): AbsoluteNote = {
    if (rawScaleType != "minor-melodic-descending") {
      val candidateStartingNote = AbsoluteNote(InternalNotationFactory.note(rawNote).note, clef.lowLedgerNote.octave)
      if (candidateStartingNote < clef.lowLedgerNote) candidateStartingNote + Interval.perfect(8) else candidateStartingNote
    } else {
      val candidateStartingNote = AbsoluteNote(InternalNotationFactory.note(rawNote).note, clef.highLedgerNote.octave)
      if (candidateStartingNote > clef.highLedgerNote) candidateStartingNote - Interval.perfect(8) else candidateStartingNote
    }
  }