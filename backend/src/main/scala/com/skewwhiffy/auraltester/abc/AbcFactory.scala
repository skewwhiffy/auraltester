package com.skewwhiffy.auraltester.abc

import scala.util.chaining.*
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, Interval, Note, Octave}

import scala.annotation.tailrec

object AbcFactory {
  lazy val interval: String => Interval = abc => IntervalFactory(abc).interval
  lazy val intervals: String => List[Interval] = abc => abc
    .split(' ')
    .map(interval)
    .toList
  lazy val note: String => AbsoluteNote = abc => NoteFactory(abc).absoluteNote
  lazy val notes: String => List[AbsoluteNote] = abc => abc
    .split(' ')
    .map(note)
    .toList
}