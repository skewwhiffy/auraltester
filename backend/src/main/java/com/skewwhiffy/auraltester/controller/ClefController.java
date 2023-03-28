package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.ClefResponse;
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory;
import com.skewwhiffy.auraltester.service.AbcService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class ClefController {
    private final InternalNotationFactory internalNotationFactory;
    private final AbcService abcService;

    public ClefController(InternalNotationFactory internalNotationFactory, AbcService abcService) {
        this.internalNotationFactory = internalNotationFactory;
        this.abcService = abcService;
    }

    @RequestMapping("/api/clef")
    ClefResponse get(String clef) {
        var clefObject = internalNotationFactory.clef(clef);
        var abc = abcService.getAbc(clefObject).getAbc();
        return new ClefResponse(abc);
    }
}
