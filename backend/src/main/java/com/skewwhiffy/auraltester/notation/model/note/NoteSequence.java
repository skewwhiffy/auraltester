package com.skewwhiffy.auraltester.notation.model.note;

import java.util.Arrays;
import java.util.List;

public interface NoteSequence {
    List<AbsoluteNote> getNotes();
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
        return () -> Arrays.asList(notes);
    }
}
