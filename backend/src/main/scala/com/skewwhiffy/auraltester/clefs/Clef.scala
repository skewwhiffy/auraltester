package com.skewwhiffy.auraltester.clefs

import com.skewwhiffy.auraltester.abc.AbcFactory
import com.skewwhiffy.auraltester.notes.AbsoluteNote

object Clef:
  lazy val treble: Clef = Clef("treble", AbcFactory.note("C"))
  lazy val alto: Clef = Clef("alto", AbcFactory.note("D,"))
  lazy val bass: Clef = Clef("bass", AbcFactory.note("E,,"))

class Clef(val name: String, val lowLedgerNote: AbsoluteNote)
