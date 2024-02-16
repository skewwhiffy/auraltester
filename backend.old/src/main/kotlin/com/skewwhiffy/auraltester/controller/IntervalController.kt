package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.dto.IntervalResponse
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.service.AbcService
import com.skewwhiffy.auraltester.service.IntervalService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IntervalController(
    private val abcService: AbcService,
    private val internalNotationFactory: InternalNotationFactory,
    private val intervalService: IntervalService
) {
    @GetMapping("/api/interval")
    fun get(
        clef: String,
        bottomNote: String,
        intervalQuality: String,
        intervalSize: Int,
        keySignature: String?
    ): IntervalResponse {
        val clefObject = internalNotationFactory.clef(clef)
        val bottomNoteObject = internalNotationFactory.getNote(bottomNote).note
        val intervalQualitySuffix = when (intervalQuality) {
            "perfect", "major" -> ""
            "minor" -> "-"
            "diminished" -> if (Interval.perfectDegrees.contains(intervalSize)) "-" else "--"
            "augmented" -> "+"
            else -> throw IllegalArgumentException(
                ("Unrecognized quality type: '$intervalQuality'")
            )
        }
        val interval = internalNotationFactory
            .getDirectedInterval("+$intervalSize$intervalQualitySuffix")
            .interval
        val keyNote = internalNotationFactory.getNote(keySignature?:"C").note
        val keySignatureObject = Key.major(keyNote)
        val intervalNotes = intervalService.getInterval(clefObject, bottomNoteObject, interval)
        return IntervalResponse (abcService.getAbc(clefObject, intervalNotes, keySignatureObject).abc)
    }
}
