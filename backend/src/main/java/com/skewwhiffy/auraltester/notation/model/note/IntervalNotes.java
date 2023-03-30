package com.skewwhiffy.auraltester.notation.model.note;

import com.skewwhiffy.auraltester.notation.model.interval.Interval;

import java.util.List;

public record IntervalNotes(AbsoluteNote bottomNote, Interval interval) implements NoteSequence {
     @Override
     public List<AbsoluteNote> getNotes() {
          return List.of(bottomNote, bottomNote.plus(interval));
     }

    /*
data class IntervalNotes (
  val bottomNote: AbsoluteNote,
  val interval: Interval
) : NoteSequence {
  override val notes: List<AbsoluteNote> = listOf(bottomNote, bottomNote + interval)
}

     */
}
