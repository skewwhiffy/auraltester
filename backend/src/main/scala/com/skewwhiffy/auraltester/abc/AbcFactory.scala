package com.skewwhiffy.auraltester.abc

import scala.util.chaining.*
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, Note, Octave}

import scala.annotation.tailrec

object AbcFactory {
  lazy val note: String => AbsoluteNote = abc => {
    new NoteFactory(abc).absoluteNote
  }
}