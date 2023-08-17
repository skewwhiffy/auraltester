package com.skewwhiffy.auraltester.notation.factory;

import com.skewwhiffy.auraltester.exception.UnrecognizedClefException;
import com.skewwhiffy.auraltester.notation.model.interval.DirectedInterval;
import com.skewwhiffy.auraltester.notation.model.key.Key;
import com.skewwhiffy.auraltester.notation.model.note.AbsoluteNote;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.skewwhiffy.auraltester.notation.model.clef.Clef;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Service
@AllArgsConstructor
public class InternalNotationFactory {
    private final ClefFactory clefFactory;
    private final NoteFactory noteFactory;
    private final IntervalFactory intervalFactory;
    private final KeyFactory keyFactory;

    public Clef clef(String clefRaw) {
        return switch (clefRaw.toLowerCase(Locale.UK)) {
            case "treble" -> clefFactory.getTreble();
            case "alto" -> clefFactory.getAlto();
            case "tenor" -> clefFactory.getTenor();
            case "bass" -> clefFactory.getBass();
            default -> throw new UnrecognizedClefException(clefRaw);
        };
    }

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
}
