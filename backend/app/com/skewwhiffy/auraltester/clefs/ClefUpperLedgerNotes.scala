package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.notes.AbsoluteNote

case class ClefUpperLedgerNotes(clef: Clef) extends ClefNotes {
  def notes: List[AbsoluteNote] = {
    val lowestNote = clef.highLedgerNote.downOne
    Range(1, 5)
      .foldLeft(List(lowestNote))((soFar, _) => soFar :+ soFar.last.upOne)
      .map(it => it.withNoteName)
  }
}
