package com.skewwhiffy.auraltester.service;

import com.skewwhiffy.auraltester.notation.model.clef.Clef;
import com.skewwhiffy.auraltester.notation.model.interval.Interval;
import com.skewwhiffy.auraltester.notation.model.note.IntervalNotes;
import com.skewwhiffy.auraltester.notation.model.note.Note;
import org.springframework.stereotype.Service;

@Service
public class IntervalService {
    public IntervalNotes getInterval(Clef clef, Note note, Interval interval) {
        return new IntervalNotes(clef.getNoteNearBottom(note), interval);
    }
}
