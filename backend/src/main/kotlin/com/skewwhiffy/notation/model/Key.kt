package com.skewwhiffy.notation.model

import java.lang.IllegalArgumentException

data class Key(val note: Note, val isMinor: Boolean = false) {
  companion object {
    /*
    private val renderableKeys: List<String> = "C G D A E B F# C# F Bb Eb Ab Db Gb Cb"
      .split(' ')
      .toList()
     */
    val cMajor: Key = Key(Note.c)
  }

  /*
  lazy val abc: String = if (isMinor) s"${note.displayString}m" else note.displayString

  def displayString: String = s"${note.displayString} ${if (isMinor) "minor" else "major"}"

*/
  fun abc(note: AbsoluteNote): String {
    return "${accidentalAbc(note.note)}${noteAbc(note)}"
  }

  /*

  def canRenderSignature: Boolean = {
    if (isMinor) {
      return relativeMajor.canRenderSignature
    }
    renderableKeys.contains(note.displayString)
  }

*/

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

  /*

  def relativeMinor: Key = if (isMinor) this else Key(note.downMajorSecond.downMajorSecond.sharp, isMinor = true)

  def relativeMajor: Key = if (isMinor) Key(note.upMajorSecond.upMajorSecond.flat) else this

  def relative: Key = if (isMinor) relativeMajor else relativeMinor

*/
  private val notes: List<Note>
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