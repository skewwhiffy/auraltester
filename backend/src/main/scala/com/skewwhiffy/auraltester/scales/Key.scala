package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.Note

object Key {
  lazy val cMajor: Key = new Key(Note.c)
}

class Key(val note: Note, val isMinor: Boolean = false) {
  lazy val abc: String = if (isMinor) s"${note.abc}m" else note.abc

  def accidentalAbc(note: Note): String = {
    ???
  }
}
