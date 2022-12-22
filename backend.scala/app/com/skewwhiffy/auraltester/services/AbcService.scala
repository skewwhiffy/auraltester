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

  def getAbc(clef: Clef): String = getAbcObject(
    s"${clef.displayName} Clef Notes",
    clef,
    clef.notes
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

  def getAbc(clef: Clef, key: Key): String = getAbc(
    s"${key.displayString} / ${key.relative.displayString}",
    clef,
    NoteSequence.empty,
    key
  )

  private def getAbc(title: String, clef: Clef, noteSequence: NoteSequence, key: Key): String = if (!key.canRenderSignature) {
    ""
  } else {
    getAbcObject(title, clef, noteSequence).includeKeySignature(key).abc
  }

  private def getAbcObject(title: String, clef: Clef, noteSequence: NoteSequence): SingleLineAbc =
    getAbcObject(title, clef, List(noteSequence))

  private def getAbcObject(title: String, clef: Clef, noteSequence: List[NoteSequence]): SingleLineAbc = SingleLineAbc(
    title.titleCase,
    clef,
    NoteLength.semibreve,
    noteSequence.map { it => it.notes }
  )

  implicit class StringTitleMaker(source: String) {
    def titleCase: String = source
      .split(' ')
      .map(it => it.substring(0, 1).toUpperCase + it.substring(1))
      .mkString(" ")
  }

}
