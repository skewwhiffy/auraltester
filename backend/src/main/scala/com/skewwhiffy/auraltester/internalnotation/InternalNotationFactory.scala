package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, DirectedInterval}
import org.springframework.stereotype.Component

@Component
class InternalNotationFactory {
  def clef(clefRaw: String): Clef = clefRaw.toLowerCase match {
    case "treble" => Clef.treble
    case "alto" => Clef.alto
    case "tenor" => Clef.tenor
    case "bass" => Clef.bass
    case _ => throw new IllegalArgumentException(s"Unrecognized clef type: '$clefRaw'")
  }

  def note(noteRaw: String): AbsoluteNote = new NoteFactory(noteRaw).absoluteNote

  def notes(notesRaw: String): List[AbsoluteNote] = notesRaw
    .split(' ')
    .map(note)
    .toList

  def directedInterval(rawInterval: String) : DirectedInterval = new IntervalFactory(rawInterval).directedInterval
  lazy val directedIntervals: String => List[DirectedInterval] = abc => abc
    .split(' ')
    .map(directedInterval)
    .toList
}