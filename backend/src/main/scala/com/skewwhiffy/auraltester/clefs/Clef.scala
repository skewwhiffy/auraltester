package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.internalnotation.InternalNotationFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote

object Clef:
  lazy val treble: Clef = Clef("treble", InternalNotationFactory.note("C"))
  lazy val alto: Clef = Clef("alto", InternalNotationFactory.note("D,"))
  lazy val bass: Clef = Clef("bass", InternalNotationFactory.note("E,,"))

class Clef(val name: String, val lowLedgerNote: AbsoluteNote)
