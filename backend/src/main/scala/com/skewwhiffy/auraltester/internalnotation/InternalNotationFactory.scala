package com.skewwhiffy.auraltester.internalnotation

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, DirectedInterval}

object InternalNotationFactory {
  lazy val clef: String => Clef = abc => abc.toLowerCase match {
    case "treble" => Clef.treble
    case "alto" => Clef.alto
    case "tenor" => Clef.tenor
    case "bass" => Clef.bass
    case _ => throw new IllegalArgumentException(s"Unrecognized clef type: '$abc'")
  }
  lazy val directedInterval: String => DirectedInterval = abc => new IntervalFactory(abc).directedInterval
  lazy val directedIntervals: String => List[DirectedInterval] = abc => abc
    .split(' ')
    .map(directedInterval)
    .toList
  lazy val note: String => AbsoluteNote = abc => new NoteFactory(abc).absoluteNote
  lazy val notes: String => List[AbsoluteNote] = abc => abc
    .split(' ')
    .map(note)
    .toList
}