package com.skewwhiffy.notation.model.key

import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.notation.model.note.Accidental
import com.skewwhiffy.notation.model.note.Note
import java.lang.IllegalArgumentException

data class Key(val note: Note, val isMinor: Boolean = false) {
  companion object {
    @Suppress("SpellCheckingInspection")
    private val renderableKeys: List<String> = "C G D A E B F# C# F Bb Eb Ab Db Gb Cb"
      .split(' ')
      .toList()

    val cMajor: Key = Key(Note.c)
  }

  val abc: String = if (isMinor) "${note.displayString}m" else note.displayString

  val displayString: String = "${note.displayString} ${if (isMinor) "minor" else "major"}"

  fun abc(note: AbsoluteNote): String {
    return "${accidentalAbc(note.note)}${noteAbc(note)}"
  }

  val canRenderSignature: Boolean = if (isMinor) relativeMajor.canRenderSignature
  else renderableKeys.contains(note.displayString)

  private fun noteAbc(note: AbsoluteNote): String {
    return note.octave.offsetFromDefault.let {
      when {
        it > 0 -> "${note.note.noteName.lowercase()}${"'".repeat(it - 1)}"
        it < 0 -> "${note.note.noteName}${",".repeat(-it)}"
        else -> note.note.noteName
      }
    }
  }

  private fun accidentalAbc(note: Note): String {
    return notes.find { it.noteName == note.noteName }.let {
      it ?: throw IllegalArgumentException("No note ${note.noteName} in scale")
      when (note.accidental) {
        it.accidental -> ""
        Accidental.natural -> "="
        else -> note.accidental.abc
      }
    }
  }

  val relativeMinor: Key
    get() = if (isMinor) this else Key(
      note.downMajorSecond.downMajorSecond.sharp,
      isMinor = true
    )

  val relativeMajor: Key get() = if (isMinor) Key(note.upMajorSecond.upMajorSecond.flat) else this

  val relative: Key get() = if (isMinor) relativeMajor else relativeMinor

  val notes: List<Note>
    get() {
      return (if (isMinor) listOf(2, 1, 2, 2, 1, 2) else listOf(2, 2, 1, 2, 2, 2))
        .scan(note) { note: Note, semitones: Int ->
          if (semitones == 2) note.upMajorSecond else note.upMajorSecond.flat
        }
    }

  /*
    override def equals(obj: Any): Boolean = obj match {
      case other: Key => note == other.note && isMinor == other.isMinor
      case _ => false
    }
  }
   */
}
