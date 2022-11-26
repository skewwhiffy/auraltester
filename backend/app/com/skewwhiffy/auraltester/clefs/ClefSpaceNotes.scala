package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.notes.AbsoluteNote

case class ClefSpaceNotes(clef: Clef) extends ClefNotes {
  def notes: List[AbsoluteNote] = {
    val lowestNote = clef.lowLedgerNote.upOne.skipOne
    Range(1, 4)
      .foldLeft(List(lowestNote))((soFar, _) => soFar :+ soFar.last.skipOne)
      .map(it => it.withLyric(it.note.displayString))
  }
}
