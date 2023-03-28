package com.skewwhiffy.auraltester.notation.model.note;

import java.util.Arrays;
import java.util.stream.Stream;

public interface NoteSequence {
    Stream<AbsoluteNote> getNotes();
    /*
interface NoteSequence {
  val notes: List<AbsoluteNote>

  companion object {
    val empty: NoteSequence = object : NoteSequence {
      override val notes: List<AbsoluteNote> = listOf()
    }

  }
}
     */
    static NoteSequence of(AbsoluteNote... notes) {
        return () -> Arrays.stream(notes);
    }
}
