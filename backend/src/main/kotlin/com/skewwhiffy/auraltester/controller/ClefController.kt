package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.dto.ClefResponse
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.service.AbcService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ClefController(private val internalNotationFactory: InternalNotationFactory, private val abcService: AbcService) {
    @GetMapping("/api/clef")
    fun get(clef: String): ClefResponse {
        val clefObject = internalNotationFactory.clef(clef)
        val abc = abcService.getAbc(clefObject).abc
        return ClefResponse(abc)
    }
}
