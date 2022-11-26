package com.skewwhiffy.auraltester.scales

import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Accidental, Interval, Note}

import scala.util.chaining.scalaUtilChainingOps

object NoteSequence {
  def empty: NoteSequence = new NoteSequence {
    val notes: List[AbsoluteNote] = List()
  }
}

trait NoteSequence {
  def notes: List[AbsoluteNote]

}
