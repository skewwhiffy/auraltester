package com.skewwhiffy.notation.model.scale

import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.notation.model.note.NoteSequence

data class Scale(
    val lowestNote: AbsoluteNote,
    private val scaleType: ScaleType,
    val direction: ScaleDirection
) : NoteSequence {
  val displayName: String = "${lowestNote.note.displayString} ${scaleType.displayName}"

  override val notes: List<AbsoluteNote>
    get() = TODO("Not yet implemented")
  /*
  def notes: List[AbsoluteNote] = scaleType
    .intervals(direction)
    .map(lowestNote.apply)
    .pipe(it => direction match {
      case ScaleDirection.ascending => it
      case ScaleDirection.descending => it.reverse
      // TODO: Check what happens with invalid choice
    })

  def abc(key: Key): String = notes.map(it => it.abc(key)).mkString("")
}
   */
}