package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.notes.AbsoluteNote

case class ClefLowerLedgerNotes(clef: Clef) extends ClefNotes {
  def notes: List[AbsoluteNote] = {
    val highestNote = clef.lowLedgerNote.upOne
    Range(1, 6)
      .foldLeft(List(highestNote))((soFar, _) => soFar :+ soFar.last.downOne)
      .map(it => it.withNoteName)
  }
}
