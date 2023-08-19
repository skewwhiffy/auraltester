package com.skewwhiffy.auraltester.notation.factory

import com.skewwhiffy.auraltester.exception.UnrecognizedClefException
import com.skewwhiffy.auraltester.notation.model.clef.Clef
import org.springframework.stereotype.Service

@Service
class InternalNotationFactory(private val clefFactory: ClefFactory) {
    /*
    private final NoteFactory noteFactory;
    private final IntervalFactory intervalFactory;
    private final KeyFactory keyFactory;
    */

    fun clef(clefRaw: String): Clef {
        return when (clefRaw.lowercase()) {
            "treble" -> clefFactory.treble
            "alto" -> clefFactory.alto
            "tenor" -> clefFactory.tenor
            "bass" -> clefFactory.bass
            else -> throw UnrecognizedClefException(clefRaw)
        }
    }
    /*

    public AbsoluteNote getNote(String noteRaw) {
        return noteFactory.getAbsoluteNote(noteRaw);
    }

    public List<AbsoluteNote> getNotes(String notesRaw) {
        return Arrays.stream(notesRaw.split(" "))
            .map(this::getNote)
            .toList();
    }

    public DirectedInterval getDirectedInterval(String rawInterval) {
        return intervalFactory.getDirectedInterval(rawInterval);
    }

    public List<DirectedInterval> getDirectedIntervals(String rawIntervals) {
        return intervalFactory.getDirectedIntervals(rawIntervals);
    }

    public Key getKey(String rawKey) {
        return keyFactory.getKey(rawKey);
    }
     */
}
