package com.skewwhiffy.service

import com.skewwhiffy.notation.model.clef.Clef
import com.skewwhiffy.notation.model.interval.Interval
import com.skewwhiffy.notation.model.note.IntervalNotes
import com.skewwhiffy.notation.model.note.Note
import org.springframework.stereotype.Service

@Service
class IntervalService {
  fun getInterval(clef: Clef, note: Note, interval: Interval): IntervalNotes =
    IntervalNotes(clef.getNoteNearBottom(note), interval)
}
