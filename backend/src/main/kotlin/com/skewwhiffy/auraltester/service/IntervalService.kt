package com.skewwhiffy.auraltester.service

import com.skewwhiffy.auraltester.notation.model.clef.Clef
import com.skewwhiffy.auraltester.notation.model.interval.Interval
import com.skewwhiffy.auraltester.notation.model.note.IntervalNotes
import com.skewwhiffy.auraltester.notation.model.note.Note
import org.springframework.stereotype.Service

@Service
class IntervalService {
    fun getInterval(clef: Clef, note: Note, interval: Interval): IntervalNotes = IntervalNotes(
        clef.getNoteNearBottom(note),
        interval
    )
}
