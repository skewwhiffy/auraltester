package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.clefs.{Clef, ClefFactory}
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, DirectedInterval}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class InternalNotationFactory(
  @Autowired clefFactory: ClefFactory,
  @Autowired intervalFactory: IntervalFactory
) {
  def clef(clefRaw: String): Clef = clefRaw.toLowerCase match {
    case "treble" => clefFactory.treble
    case "alto" => clefFactory.alto
    case "tenor" => clefFactory.tenor
    case "bass" => clefFactory.bass
    case _ => throw new IllegalArgumentException(s"Unrecognized clef type: '$clefRaw'")
  }

  def note(noteRaw: String): AbsoluteNote = new NoteFactory(noteRaw).absoluteNote

  def notes(notesRaw: String): List[AbsoluteNote] = notesRaw
    .split(' ')
    .map(note)
    .toList

  def directedInterval(rawInterval: String) : DirectedInterval = intervalFactory.getDirectedInterval(rawInterval)
  lazy val directedIntervals: String => List[DirectedInterval] = abc => abc
    .split(' ')
    .map(directedInterval)
    .toList
}