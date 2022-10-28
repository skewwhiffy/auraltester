package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval, Note}
import com.skewwhiffy.auraltester.scales.{Scale, ScaleType}
import org.springframework.stereotype.Service
import util.chaining.scalaUtilChainingOps

@Service
class ScaleService {
  def getScale(clef: Clef, note: Note, scaleType: ScaleType) : Scale = {
    getStartingNote(clef, note, scaleType).pipe(it => new Scale(it, scaleType))
  }

  private def getStartingNote(clef: Clef, note: Note, scaleType: ScaleType): AbsoluteNote = {
    if (scaleType != ScaleType.minorMelodicDescending) {
      val candidateStartingNote = new AbsoluteNote(note, clef.lowLedgerNote.octave)
      if (candidateStartingNote < clef.lowLedgerNote) candidateStartingNote + Interval.perfect(8) else candidateStartingNote
    } else {
      val candidateStartingNote = new AbsoluteNote(note, clef.highLedgerNote.octave)
      if (candidateStartingNote > clef.highLedgerNote) candidateStartingNote - Interval.perfect(8) else candidateStartingNote
    }
  }
}