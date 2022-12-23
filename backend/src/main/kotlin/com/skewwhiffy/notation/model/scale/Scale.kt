package com.skewwhiffy.notation.model.scale

import com.skewwhiffy.notation.model.key.Key
import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.notation.model.note.NoteSequence

data class Scale(
  val lowestNote: AbsoluteNote,
  private val scaleType: ScaleType,
  val direction: ScaleDirection,
) : NoteSequence {
  val displayName: String = "${lowestNote.note.displayString} ${scaleType.displayName}"

  override val notes: List<AbsoluteNote>
    get() = scaleType
      .intervals(direction)
      .map(lowestNote::apply)
      .let {
        when (direction) {
          ScaleDirection.ASCENDING -> it
          ScaleDirection.DESCENDING -> it.reversed()
        }
      }

  fun abc(key: Key): String = notes.joinToString("") { it.abc(key) }
}