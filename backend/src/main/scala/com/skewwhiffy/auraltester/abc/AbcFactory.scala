package com.skewwhiffy.auraltester.abc

import scala.util.chaining.*
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, DirectedInterval, Interval, Note, Octave}

import scala.annotation.tailrec

object AbcFactory {
  lazy val directedInterval: String => DirectedInterval = abc => IntervalFactory(abc).directedInterval
  lazy val directedIntervals: String => List[DirectedInterval] = abc => abc
    .split(' ')
    .map(directedInterval)
    .toList
  lazy val note: String => AbsoluteNote = abc => NoteFactory(abc).absoluteNote
  lazy val notes: String => List[AbsoluteNote] = abc => abc
    .split(' ')
    .map(note)
    .toList
}