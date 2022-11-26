package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.notes.AbsoluteNote

case class ClefLineNotes(clef: Clef) extends ClefNotes {
  def notes: List[AbsoluteNote] = {
    val lowestNote = clef.lowLedgerNote.skipOne
    Range(1, 5)
      .foldLeft(List(lowestNote))((soFar, _) => soFar :+ soFar.last.skipOne)
      .map(it => it.withNoteName)
  }
}
