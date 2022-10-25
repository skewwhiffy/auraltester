package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.controller.dto.ScaleResponse
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval, Note}
import com.skewwhiffy.auraltester.scales.Scale
import org.springframework.http.{MediaType, ResponseEntity}
import org.springframework.web.bind.annotation.{GetMapping, PathVariable, RequestMapping, RestController}

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
    val candidateStartingNote = AbsoluteNote(InternalNotationFactory.note(rawNote).note, clef.lowLedgerNote.octave)
    val startingNote = if (candidateStartingNote < clef.lowLedgerNote) candidateStartingNote + Interval.perfect(8) else
      candidateStartingNote
    val scale = Scale.major(startingNote)
    val abc =
      s"""
X:1
T:TODO, the scale name
K:clef=${clef.name}
L:1
${scale.notes.map(it => s"${it.abc}").mkString(" ")}
    """.stripMargin
    ScaleResponse(abc)
  }