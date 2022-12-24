package com.skewwhiffy.notation.factory

import com.skewwhiffy.notation.model.note.AbsoluteNote
import com.skewwhiffy.notation.model.clef.Clef
import org.springframework.stereotype.Service

@Service
class ClefFactory(private val noteFactory: NoteFactory) {
  val treble get() = Clef("treble", AbsoluteNote.middleC, noteFactory.getAbsoluteNote("a"))

  val alto get() = Clef("alto", noteFactory.getAbsoluteNote("D,"), noteFactory.getAbsoluteNote("B"))

  val tenor get() = Clef("tenor", noteFactory.getAbsoluteNote("B,,"), noteFactory.getAbsoluteNote("G"))

  val bass get() = Clef("bass", noteFactory.getAbsoluteNote("E,,"), AbsoluteNote.middleC)
}