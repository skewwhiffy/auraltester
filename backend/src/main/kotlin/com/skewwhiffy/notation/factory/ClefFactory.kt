package com.skewwhiffy.notation.factory

import com.skewwhiffy.notation.model.AbsoluteNote
import com.skewwhiffy.notation.model.Clef
import org.springframework.stereotype.Service

@Service
class ClefFactory(noteFactory: NoteFactory) {
  val treble = Clef("treble", AbsoluteNote.middleC, noteFactory.getAbsoluteNote("a"))

  val alto = Clef("alto", noteFactory.getAbsoluteNote("D,"), noteFactory.getAbsoluteNote("B"))

  val tenor = Clef("tenor", noteFactory.getAbsoluteNote("B,,"), noteFactory.getAbsoluteNote("G"))

  val bass = Clef("bass", noteFactory.getAbsoluteNote("E,,"), AbsoluteNote.middleC)
}