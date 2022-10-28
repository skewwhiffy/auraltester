package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval, Note}
import com.skewwhiffy.auraltester.scales.{Scale, ScaleDirection, ScaleType}
import org.springframework.stereotype.Service

import util.chaining.scalaUtilChainingOps

@Service
class ScaleService {
  def getScale(clef: Clef, note: Note, scaleType: ScaleType, direction: ScaleDirection): Scale = {
    getStartingNote(clef, note).pipe(it => new Scale(it, scaleType, direction))
  }

  private def getStartingNote(clef: Clef, note: Note): AbsoluteNote = {
    val candidateStartingNote = new AbsoluteNote(note, clef.lowLedgerNote.octave)
    if (candidateStartingNote < clef.lowLedgerNote) candidateStartingNote + Interval.perfect(8) else candidateStartingNote
  }
}