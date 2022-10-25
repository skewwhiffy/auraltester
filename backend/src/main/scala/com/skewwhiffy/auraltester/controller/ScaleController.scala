package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.abc.AbcFactory
import com.skewwhiffy.auraltester.controller.dto.ScaleResponse
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
    println(rawClef)
    val clef = AbcFactory.clef(rawClef)
    println(rawNote)
    println(rawScaleType)
    val abc =
      s"""
X:1
T:TODO, the scale name
K:clef=${clef.name}
L:1
${clef.lowLedgerNote}
    """.stripMargin
    ScaleResponse(abc)
  }