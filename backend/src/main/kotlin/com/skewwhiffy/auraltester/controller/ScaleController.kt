package com.skewwhiffy.auraltester.controller

import com.skewwhiffy.auraltester.dto.ScaleResponse
import com.skewwhiffy.auraltester.notation.factory.InternalNotationFactory
import com.skewwhiffy.auraltester.notation.factory.ScaleTypeFactory
import com.skewwhiffy.auraltester.notation.model.key.Key
import com.skewwhiffy.auraltester.notation.model.scale.ScaleDirection
import com.skewwhiffy.auraltester.service.AbcService
import com.skewwhiffy.auraltester.service.ScaleService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ScaleController(
    private val abcService: AbcService,
    private val internalNotationFactory: InternalNotationFactory,
    private val scaleService: ScaleService,
    private val scaleTypeFactory: ScaleTypeFactory
) {

    @GetMapping("/api/scale")
    fun get(clef: String, note: String, scaleType: String, direction: String): ScaleResponse {
        val clefObject = internalNotationFactory.clef(clef)
        val noteObject = internalNotationFactory.getNote(note).note
        val scaleTypeAndMinor = when (scaleType) {
            "major" -> scaleTypeFactory.major to false
            "minor-harmonic" -> scaleTypeFactory.minorHarmonic to true
            "minor-melodic" -> scaleTypeFactory.minorMelodic to true
            else -> throw IllegalArgumentException("Unrecognized scale type: $scaleType")
        }
        val scaleTypeObject = scaleTypeAndMinor.first
        val isMinor = scaleTypeAndMinor.second
        val directionObject = when (direction) {
            "ascending" -> ScaleDirection.ASCENDING
            "descending" -> ScaleDirection.DESCENDING
            else -> throw IllegalArgumentException("Unrecognized direction: '$direction'")
        }
        val scale = scaleService.getScale(clefObject, noteObject, scaleTypeObject, directionObject)
        val lowestNote = scale.lowestNote.note
        val key = if (isMinor) Key.minor(lowestNote) else Key.major(lowestNote)
        return ScaleResponse(
            abcService.getAbc(clefObject, scale).abc,
            abcService.getAbc(clefObject, scale, key).abc
        )
    }
}
