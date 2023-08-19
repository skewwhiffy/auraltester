package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.service.AbcService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
public class ClefController(private val internalNotationFactory: InternalNotationFactory, private val abcService: AbcService) {
    @GetMapping("/api/clef")
    fun get(clef: String): ClefResponse {
        var clefObject = internalNotationFactory.clef(clef);
        var abc = abcService.getAbc(clefObject).getAbc();
        return new ClefResponse(abc);
    }
}
