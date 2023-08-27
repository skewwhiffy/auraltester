package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.exception.UnrecognizedClefException
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval
import org.springframework.stereotype.Service

@Service
class InternalNotationFactory(
    private val clefFactory: ClefFactory,
    private val noteFactory: NoteFactory,
    private val intervalFactory: IntervalFactory,
    private val keyFactory: KeyFactory
) {
    fun clef(clefRaw: String): Clef {
        return when (clefRaw.lowercase()) {
            "treble" -> clefFactory.treble
            "alto" -> clefFactory.alto
            "tenor" -> clefFactory.tenor
            "bass" -> clefFactory.bass
            else -> throw UnrecognizedClefException(clefRaw)
        }
    }

    fun getNote(noteRaw: String) = noteFactory.getAbsoluteNote(noteRaw)

    fun getNotes(notesRaw: String) = notesRaw.split(" ").map(::getNote)

    fun getDirectedInterval(rawInterval: String) = intervalFactory.getDirectedInterval(rawInterval)

    fun getDirectedIntervals(rawIntervals: String) = intervalFactory.getDirectedIntervals(rawIntervals)

    fun getKey(rawKey: String) = keyFactory.getKey(rawKey)
}
