package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.dto.KeySignatureResponse
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.service.AbcService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

import java.util.Optional

@RestController
class KeySignatureController(
    private val internalNotationFactory: InternalNotationFactory,
    private val abcService: AbcService
) {
    @GetMapping("/api/keySignature")
    fun get(
        @RequestParam clef: String,
        @RequestParam keySignature: String?
    ): KeySignatureResponse {
        val clefObject = internalNotationFactory.clef(clef)
        val note = internalNotationFactory
            .getNote(keySignature ?: "C")
            .note
        val key = Key.major(note)
        val abc = abcService.getAbc(clefObject, key).abc
        return KeySignatureResponse (abc)
    }
}
