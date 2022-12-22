package com.skewwhiffy.controller

import com.skewwhiffy.dto.ClefResponse
import com.skewwhiffy.notation.factory.InternalNotationFactory
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import com.skewwhiffy.service.AbcService

@RestController
class ClefController(
  private val internalNotationFactory: InternalNotationFactory,
  private val abcService: AbcService
) {

  @RequestMapping("/api/clef")
  fun get(clef: String): ClefResponse {
    val clefObject = internalNotationFactory.clef(clef)
    return internalNotationFactory
      .getNote("C")
      .note
      .let { abcService.getAbc(clefObject) }
      .let { ClefResponse(it) }
  }
}