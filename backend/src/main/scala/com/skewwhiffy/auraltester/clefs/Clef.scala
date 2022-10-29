package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote

object Clef {
  private val internalNotationFactory = new InternalNotationFactory()
  lazy val treble: Clef = new Clef("treble", AbsoluteNote.middleC, internalNotationFactory.note("a"))
  lazy val alto: Clef = new Clef("alto", internalNotationFactory.note("D,"), internalNotationFactory.note("B"))
  lazy val tenor: Clef = new Clef("tenor", internalNotationFactory.note("B,,"), internalNotationFactory.note("G"))
  lazy val bass: Clef = new Clef("bass", internalNotationFactory.note("E,,"), AbsoluteNote.middleC)
}

class Clef(val abc: String, val lowLedgerNote: AbsoluteNote, val highLedgerNote: AbsoluteNote)
