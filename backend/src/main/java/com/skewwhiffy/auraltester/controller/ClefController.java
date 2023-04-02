package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.ClefResponse;
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory;
import com.skewwhiffy.auraltester.service.AbcService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ClefController {
    private final InternalNotationFactory internalNotationFactory;
    private final AbcService abcService;

    @RequestMapping("/api/clef")
    public ClefResponse get(String clef) {
        var clefObject = internalNotationFactory.clef(clef);
        var abc = abcService.getAbc(clefObject).getAbc();
        return new ClefResponse(abc);
    }
}
