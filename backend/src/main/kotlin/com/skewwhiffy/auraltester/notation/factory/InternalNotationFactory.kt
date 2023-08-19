package com.skewwhiffy.auraltester.notation.factory

import org.springframework.stereotype.Service
import java.util.*

@Service
class InternalNotationFactory {
    /*
    private final ClefFactory clefFactory;
    private final NoteFactory noteFactory;
    private final IntervalFactory intervalFactory;
    private final KeyFactory keyFactory;
    */

    fun clef(clefRaw: String): ClefResponse {
        return when (clefRaw.lowercase()) {
            "treble" -> clefFactory.getTreble();
            "alto" -> clefFactory.getAlto();
            "tenor" -> clefFactory.getTenor();
            "bass" -> clefFactory.getBass();
            else -> throw UnrecognizedClefException (clefRaw);
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
