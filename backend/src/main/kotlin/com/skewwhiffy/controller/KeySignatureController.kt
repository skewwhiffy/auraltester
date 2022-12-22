package com.skewwhiffy.controller

import com.skewwhiffy.dto.KeySignatureResponse
import com.skewwhiffy.notation.factory.InternalNotationFactory
import com.skewwhiffy.notation.model.Key
import com.skewwhiffy.service.AbcService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class KeySignatureController(
  private val internalNotationFactory: InternalNotationFactory,
  private val abcService: AbcService,
) {
  @RequestMapping("/api/keySignature")
  fun get(
    @RequestParam clef: String,
    @RequestParam keySignature: String?,
  ): KeySignatureResponse {
    val clefObject = internalNotationFactory.clef(clef)
    return internalNotationFactory
      .getNote(keySignature ?: ("C"))
      .note
      .let { Key(it) }
      .let { abcService.getAbc(clefObject, it) }
      .let { KeySignatureResponse(it) }
  }
}
