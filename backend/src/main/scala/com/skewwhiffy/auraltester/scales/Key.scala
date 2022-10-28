package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.Note

object Key {
  lazy val cMajor: Key = new Key(Note.c)
}

class Key(val note: Note, val minor: Boolean = false) {
  lazy val abc: String = if (minor) s"${note.abc}m" else note.abc
}
