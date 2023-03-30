package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.ScaleResponse;
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory;
import com.skewwhiffy.auraltester.notation.factory.ScaleTypeFactory;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection;
import com.skewwhiffy.auraltester.service.AbcService;
import com.skewwhiffy.auraltester.service.ScaleService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;

@RestController
@AllArgsConstructor
public class ScaleController {
    private final AbcService abcService;
    private final InternalNotationFactory internalNotationFactory;
    private final ScaleService scaleService;
    private final ScaleTypeFactory scaleTypeFactory;

    @RequestMapping("/api/scale")
    public ScaleResponse get(String clef, String note, String scaleType, String direction) {
        val clefObject = internalNotationFactory.clef(clef);
        val noteObject = internalNotationFactory.getNote(note).note();
        val scaleTypeAndMinor = switch (scaleType) {
            case "major" -> Pair.of(scaleTypeFactory.getMajor(), false);
            case "minor-harmonic" -> Pair.of(scaleTypeFactory.getMinorHarmonic(), true);
            case "minor-melodic" -> Pair.of(scaleTypeFactory.getMinorMelodic(), true);
            default -> throw new IllegalArgumentException(
                    MessageFormat.format("Unrecognized scale type: {0}", scaleType)
            );
        };
        val scaleTypeObject = scaleTypeAndMinor.getFirst();
        val isMinor = scaleTypeAndMinor.getSecond();
        val directionObject = switch (direction) {
            case "ascending" -> ScaleDirection.ASCENDING;
            case "descending" -> ScaleDirection.DESCENDING;
            default -> throw new IllegalArgumentException(
                    MessageFormat.format("Unrecognized direction: '{0}'", direction)
            );
        };
        val scale = scaleService.getScale(clefObject, noteObject, scaleTypeObject, directionObject);
        val lowestNote = scale.lowestNote().note();
        val key = isMinor ? Key.minor(lowestNote) : Key.major(lowestNote);
        return new ScaleResponse(
                abcService.getAbc(clefObject, scale).getAbc(),
                abcService.getAbc(clefObject, scale, key).getAbc()
        );
    }
}
