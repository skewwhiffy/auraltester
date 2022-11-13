package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.abc.SingleLineAbc
import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.NoteLength
import com.skewwhiffy.auraltester.scales.{IntervalNotes, Key, NoteSequence, Scale}

class AbcService {
  def getAbc(clef: Clef, scale: Scale): String = getAbcObject(
    s"${scale.displayName} ${scale.direction.displayString}",
    clef,
    scale
  ).abc

  def getAbc(clef: Clef, scale: Scale, key: Key): String = getAbc(
    s"${scale.displayName} ${scale.direction.displayString} (with key signature)",
    clef,
    scale,
    key
  )

  def getAbc(clef: Clef, intervalNotes: IntervalNotes, key: Key): String = getAbc(
    intervalNotes.interval.displayString,
    clef,
    intervalNotes,
    key
  )

  private def getAbc(title: String, clef: Clef, noteSequence: NoteSequence, key: Key): String = if (!key.canRenderSignature) {
    ""
  } else {
    getAbcObject(title, clef, noteSequence).includeKeySignature(key).abc
  }

  private def getAbcObject(title: String, clef: Clef, noteSequence: NoteSequence): SingleLineAbc = new SingleLineAbc(
    title,
    clef,
    NoteLength.semibreve,
    noteSequence.notes
  )

}
