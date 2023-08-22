package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.ScaleResponse;
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory;
import com.skewwhiffy.auraltester.notation.factory.ScaleTypeFactory;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection;
import com.skewwhiffy.auraltester.service.AbcService;
import com.skewwhiffy.auraltester.service.ScaleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.RuntimeException

import java.text.MessageFormat;

@RestController
class ScaleController(
    private val abcService: AbcService,
    private val internalNotationFactory: InternalNotationFactory,
    private val scaleService: ScaleService,
    private val scaleTypeFactory: ScaleTypeFactory
) {

    @GetMapping("/api/scale")
    fun get( clef:String,  note:String,  scaleType:String,  direction:String): ScaleResponse {
        throw RuntimeException()
        /*
        val clefObject = internalNotationFactory.clef(clef);
        val noteObject = internalNotationFactory.getNote(note).note();
        val scaleTypeAndMinor = switch(scaleType) {
            case "major" -> Pair.of(scaleTypeFactory.getMajor(), false);
            case "minor-harmonic" -> Pair.of(scaleTypeFactory.getMinorHarmonic(), true);
            case "minor-melodic" -> Pair.of(scaleTypeFactory.getMinorMelodic(), true);
            default -> throw new IllegalArgumentException(
            MessageFormat.format("Unrecognized scale type: {0}", scaleType)
            );
        };
        val scaleTypeObject = scaleTypeAndMinor.getFirst();
        val isMinor = scaleTypeAndMinor.getSecond();
        val directionObject = switch(direction) {
            case "ascending" -> ScaleDirection.ASCENDING;
            case "descending" -> ScaleDirection.DESCENDING;
            default -> throw new IllegalArgumentException(
            MessageFormat.format("Unrecognized direction: '{0}'", direction)
            );
        };
        val scale = scaleService.getScale(clefObject, noteObject, scaleTypeObject, directionObject);
        val lowestNote = scale.lowestNote().note();
        val key = isMinor ? Key.minor(lowestNote) : Key.major(lowestNote);
        return new ScaleResponse (
                abcService.getAbc(clefObject, scale).getAbc(),
        abcService.getAbc(clefObject, scale, key).getAbc()
        );
         */
    }
}
