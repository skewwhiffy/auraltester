package com.skewwhiffy.notation.model.abc

import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.notation.model.clef.Clef
import com.skewwhiffy.notation.model.key.Key
import com.skewwhiffy.notation.model.note.NoteLength

data class SingleLineAbc(
  private val displayName: String?,
  private val clef: Clef,
  private val noteLength: NoteLength,
  private val keySignature: Key?,
  private val notes: List<List<AbsoluteNote>>,
) : AbcProvider {
  constructor(
    displayName: String?,
    clef: Clef,
    noteLength: NoteLength,
    notes: List<List<AbsoluteNote>>,
  ) : this(displayName, clef, noteLength, null, notes)

  constructor(
    clef: Clef,
    noteLength: NoteLength,
    notes: List<List<AbsoluteNote>>,
  ) : this(null, clef, noteLength, null, notes)

  fun includeKeySignature(key: Key): SingleLineAbc = SingleLineAbc(
    displayName,
    clef,
    noteLength,
    key,
    notes
  )

  override val abc: String
    get() {
      fun barAbc(value: List<AbsoluteNote>): String {
        return value.joinToString("") { it.abc(keySignature ?: Key.cMajor) }
      }

      val notesAbc = notes.joinToString("|") { barAbc(it) } + "|"
      val words = notes.flatten().joinToString(" ") { it.wordAbc }

      return listOf(
        "X:1",
        displayName?.let { "T:$it" } ?: "",
        "K:clef=${clef.abc}",
        keySignature?.let { "K:${it.abc}" } ?: "",
        "L:${noteLength.abc}",
        notesAbc,
        "w:$words"
      )
        .filter { it.isNotEmpty() }
        .joinToString(System.lineSeparator())
    }
}