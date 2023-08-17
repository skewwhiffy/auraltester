package com.skewwhiffy.auraltester.controller;

import com.skewwhiffy.auraltester.dto.IntervalResponse;
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory;
import com.skewwhiffy.auraltester.notation.model.interval.Interval;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.service.AbcService;
import com.skewwhiffy.auraltester.service.IntervalService;
import lombok.AllArgsConstructor;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class IntervalController {
    private final AbcService abcService;
    private final InternalNotationFactory internalNotationFactory;
    private final IntervalService intervalService;

    @GetMapping("/api/interval")
    public IntervalResponse get(
            String clef,
            String bottomNote,
            String intervalQuality,
            int intervalSize,
            Optional<String> keySignature
    ) {
        val clefObject = internalNotationFactory.clef(clef);
        val bottomNoteObject = internalNotationFactory.getNote(bottomNote).note();
        val intervalQualitySuffix = switch (intervalQuality) {
            case "perfect", "major" -> "";
            case "minor" -> "-";
            case "diminished" -> Interval.perfectDegrees.contains(intervalSize) ? "-" : "--";
            case "augmented" -> "+";
            default -> throw new IllegalArgumentException(
                    MessageFormat.format("Unrecognized quality type: '{0}'", intervalQuality)
            );
        };
        val interval = internalNotationFactory
                .getDirectedInterval("+" + intervalSize + intervalQualitySuffix)
                .interval();
        val keyNote = internalNotationFactory.getNote(keySignature.orElse("C")).note();
        val keySignatureObject = Key.major(keyNote);
        val intervalNotes = intervalService.getInterval(clefObject, bottomNoteObject, interval);
        return new IntervalResponse(abcService.getAbc(clefObject, intervalNotes, keySignatureObject).getAbc());
    }
}
