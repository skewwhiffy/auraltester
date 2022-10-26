package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote

object Clef:
  lazy val treble: Clef = Clef("treble", AbsoluteNote.middleC, InternalNotationFactory.note("a"))
  lazy val alto: Clef = Clef("alto", InternalNotationFactory.note("D,"), InternalNotationFactory.note("B"))
  lazy val tenor: Clef = Clef("tenor", InternalNotationFactory.note("B,,"), InternalNotationFactory.note("G"))
  lazy val bass: Clef = Clef("bass", InternalNotationFactory.note("E,,"), AbsoluteNote.middleC)

class Clef(val name: String, val lowLedgerNote: AbsoluteNote, val highLedgerNote: AbsoluteNote)
