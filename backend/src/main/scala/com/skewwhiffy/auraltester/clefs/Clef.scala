package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote

object Clef {
  lazy val treble: Clef = new Clef("treble", AbsoluteNote.middleC, InternalNotationFactory.note("a"))
  lazy val alto: Clef = new Clef("alto", InternalNotationFactory.note("D,"), InternalNotationFactory.note("B"))
  lazy val tenor: Clef = new Clef("tenor", InternalNotationFactory.note("B,,"), InternalNotationFactory.note("G"))
  lazy val bass: Clef = new Clef("bass", InternalNotationFactory.note("E,,"), AbsoluteNote.middleC)
}

class Clef(val abc: String, val lowLedgerNote: AbsoluteNote, val highLedgerNote: AbsoluteNote)
