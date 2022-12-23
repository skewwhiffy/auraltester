package com.skewwhiffy.service

import com.skewwhiffy.notation.model.clef.Clef
import com.skewwhiffy.notation.model.key.Key
import com.skewwhiffy.notation.model.note.NoteLength
import com.skewwhiffy.notation.model.note.NoteSequence
import com.skewwhiffy.notation.model.abc.SingleLineAbc
import com.skewwhiffy.notation.model.scale.Scale
import org.springframework.stereotype.Service

@Service
class AbcService {
  fun getAbc(clef: Clef, scale: Scale): String = getAbcObject(
    "${scale.displayName} ${scale.direction.displayString}",
    clef,
    scale
  ).abc

  fun getAbc(clef: Clef): String {
    return getAbcObject(
      "${clef.displayName} Clef Notes",
      clef,
      clef.notes
    ).abc
  }

  fun getAbc(clef: Clef, scale: Scale, key: Key): String = getAbc(
    "${scale.displayName} ${scale.direction.displayString} (with key signature)",
    clef,
    scale,
    key
  )

  /*
  def getAbc(clef: Clef, intervalNotes: IntervalNotes, key: Key): String = getAbc(
    intervalNotes.interval.displayString,
    clef,
    intervalNotes,
    key
  )
*/

  fun getAbc(clef: Clef, key: Key): String = getAbc(
    "${key.displayString} / ${key.relative.displayString}",
    clef,
    NoteSequence.empty,
    key
  )

  private fun getAbc(title: String, clef: Clef, noteSequence: NoteSequence, key: Key): String =
    if (!key.canRenderSignature) {
      ""
    } else {
      getAbcObject(title, clef, noteSequence).includeKeySignature(key).abc
    }

  private fun getAbcObject(
    title: String,
    clef: Clef,
    noteSequence: NoteSequence,
  ): SingleLineAbc =
    getAbcObject(title, clef, listOf(noteSequence))

  private fun getAbcObject(
    title: String,
    clef: Clef,
    noteSequence: List<NoteSequence>,
  ): SingleLineAbc = SingleLineAbc(
    title.titleCase,
    clef,
    NoteLength.semibreve,
    noteSequence.map { it.notes }
  )

  private val String.titleCase
    get() = split(" ").joinToString(" ") { it.substring(0, 1).uppercase() + it.substring(1) }
  /*
  implicit class StringTitleMaker(source: String) {
    def titleCase: String = source
      .split(' ')
      .map(it => it.substring(0, 1).toUpperCase + it.substring(1))
      .mkString(" ")
  }
   */
}