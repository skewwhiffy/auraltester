package com.skewwhiffy.auraltester.services

import com.skewwhiffy.auraltester.clefs.Clef
import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.{AbsoluteNote, Interval, Note}
import com.skewwhiffy.auraltester.scales.{Scale, ScaleType}
import org.springframework.stereotype.Service
import util.chaining.scalaUtilChainingOps

@Service
class ScaleService:
  lazy val getScale: (Clef, Note, ScaleType) => Scale = (clef, note, scaleType) => {
    getStartingNote(clef, note, scaleType).pipe(it => Scale(it, scaleType))
  }

  private def getStartingNote(clef: Clef, note: Note, scaleType: ScaleType): AbsoluteNote = {
    if (scaleType != ScaleType.minorMelodicDescending) {
      val candidateStartingNote = AbsoluteNote(note, clef.lowLedgerNote.octave)
      if (candidateStartingNote < clef.lowLedgerNote) candidateStartingNote + Interval.perfect(8) else candidateStartingNote
    } else {
      val candidateStartingNote = AbsoluteNote(note, clef.highLedgerNote.octave)
      if (candidateStartingNote > clef.highLedgerNote) candidateStartingNote - Interval.perfect(8) else candidateStartingNote
    }
  }
