package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.clefs.{Clef, ClefFactory}
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, DirectedInterval}
import com.skewwhiffy.auraltester.scales.Key

import javax.inject.Inject

class InternalNotationFactory @Inject()(
  clefFactory: ClefFactory,
  intervalFactory: IntervalFactory,
  keyFactory: KeyFactory,
  noteFactory: NoteFactory
) {
  def clef(clefRaw: String): Clef = clefRaw.toLowerCase match {
    case "treble" => clefFactory.treble
    case "alto" => clefFactory.alto
    case "tenor" => clefFactory.tenor
    case "bass" => clefFactory.bass
    case _ => throw new IllegalArgumentException(s"Unrecognized clef type: '$clefRaw'")
  }

  def getNote(noteRaw: String): AbsoluteNote = noteFactory.getAbsoluteNote(noteRaw)

  def getNotes(notesRaw: String): List[AbsoluteNote] = notesRaw
    .split(' ')
    .map(getNote)
    .toList

  def getDirectedInterval(rawInterval: String): DirectedInterval = intervalFactory
    .getDirectedInterval(rawInterval)

  def getDirectedIntervals(rawIntervals: String): List[DirectedInterval] = intervalFactory
    .getDirectedIntervals(rawIntervals)

  def getKey(rawKey: String): Key = keyFactory.getKey(rawKey)
}