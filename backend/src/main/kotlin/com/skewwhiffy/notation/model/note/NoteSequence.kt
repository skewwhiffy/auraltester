package com.skewwhiffy.notation.model.note

interface NoteSequence {
  val notes: List<AbsoluteNote>

  companion object {
    val empty: NoteSequence = object : NoteSequence {
      override val notes: List<AbsoluteNote> = listOf()
    }

    fun of(vararg notes: AbsoluteNote) = object: NoteSequence {
      override val notes: List<AbsoluteNote> = notes.toList()
    }
  }
}